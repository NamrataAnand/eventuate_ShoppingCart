pipeline {
	agent none    
stages {	
        stage('Building') {
		agent{
			docker {
			    image 'maven:3-alpine'
			    args '-v $HOME/.m2:/root/.m2'
			}
		}
		steps{
			sh'mvn clean package -s settings.xml docker:build'
		}
	}
	stage('Testing') {
		agent any
		steps {
			//sh 'mvn test'
			sh 'docker network create my-net'
		}
	}
	stage('Database') {
		parallel {
			stage('MySql') {
				agent any
				steps {
					sh 'docker run -d \
					    --name mysql \
					    --network=my-net \
					    -p 3306:3306 \
					    -e MYSQL_ROOT_PASSWORD=rootpassword \
					    -e MYSQL_USER=mysqluser \
					    -e MYSQL_PASSWORD=mysqlpw \
					    eventuateio/eventuate-tram-sagas-mysql:0.3.0.RELEASE'
				}
			}
			stage('MongoDB') {
				agent any
				steps {
					sh 'docker run -d \
					    --name mongodb \
					    --network=my-net \
					    -p 27017:27017 \
					    -e MONGO_DATA_DIR=/data/db \
					    -e MONGO_LOG_DIR=/dev/null \
					    -v /data/db:/data/db \
					    mongo:3.0.15'
				}
			}
		}
	}
	stage('Zookeeper') {
			agent any
			steps {
				sh 'docker run -d \
					--name zookeeper \
					--network=my-net \
					-p 2181:2181 \
					-p 2888:2888 \
					-p 3888:3888 \
					eventuateio/eventuateio-local-zookeeper:0.14.0'
			}
	}
	stage('Kafka') {
			agent any
			steps {
				sh 'docker run -d \
				--name kafka \
				--network=my-net \
				-p 9092:9092 \
				--link zookeeper \
				-e ADVERTISED_HOST_NAME=${DOCKER_HOST_IP} \
				-e "KAFKA_HEAP_OPTS=-Xmx320m -Xms320m" \
				-e ZOOKEEPER_SERVERS=zookeeper:2181 \
			eventuateio/eventuateio-local-kafka:0.14.0'
			}
	}
	stage('Cdcservice') {
			agent any
			steps {
				sh 'docker run -d \
				--name cdcservice \
				--network=my-net \
				-p 8099:8080 \
				--link mysql \
				--link kafka \
				--link zookeeper \
				-e SPRING_DATASOURCE_URL=jdbc:mysql://mysql/eventuate \
				-e SPRING_DATASOURCE_USERNAME=mysqluser \
				-e SPRING_DATASOURCE_PASSWORD=mysqlpw \
				-e SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.jdbc.Driver \
				-e EVENTUATELOCAL_KAFKA_BOOTSTRAP_SERVERS=kafka:9092 \
				-e EVENTUATELOCAL_ZOOKEEPER_CONNECTION_STRING=zookeeper:2181 \
				-e EVENTUATELOCAL_CDC_DB_USER_NAME=root \
				-e EVENTUATELOCAL_CDC_DB_PASSWORD=rootpassword \
				-e EVENTUATELOCAL_CDC_BINLOG_CLIENT_ID=1234567890 \
				eventuateio/eventuate-tram-cdc-mysql-service:0.3.0.RELEASE'
			}
	}
	stage('Deployment') {
		parallel {
			stage('Customer Service') {
				agent any
				steps {
					sh 'docker run -d \
					--name customer-service \
					--network=my-net \
					-p 5001:8085 \
					--link mysql \
					--link kafka \
					--link zookeeper \
					--link cdcservice \
					-e SPRING_DATASOURCE_URL=jdbc:mysql://mysql/eventuate \
					-e SPRING_DATASOURCE_USERNAME=mysqluser \
					-e SPRING_DATASOURCE_PASSWORD=mysqlpw \
					-e SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.jdbc.Driver \
					-e SPRING_DATASOURCE_TIMEOUT=20000 \
					-e EVENTUATELOCAL_KAFKA_BOOTSTRAP_SERVERS=kafka:9092 \
					-e EVENTUATELOCAL_ZOOKEEPER_CONNECTION_STRING=zookeeper:2181 \
					-e MONGODB=mongodb:27017/orderdb \
					carloselpapa10/customer-service'
				}
			}
			stage('Order Service') {
				agent any
				steps {
					sh 'docker run -d \
					--name order-service \
					--network=my-net \
					-p 5000:8080 \
					--link mysql \
					--link kafka \
					--link zookeeper \
					--link cdcservice \
					-e SPRING_DATASOURCE_URL=jdbc:mysql://mysql/eventuate \
					-e SPRING_DATASOURCE_USERNAME=mysqluser \
					-e SPRING_DATASOURCE_PASSWORD=mysqlpw \
					-e SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.jdbc.Driver \
					-e SPRING_DATASOURCE_TIMEOUT=20000 \
					-e EVENTUATELOCAL_KAFKA_BOOTSTRAP_SERVERS=kafka:9092 \
					-e EVENTUATELOCAL_ZOOKEEPER_CONNECTION_STRING=zookeeper:2181 \
					-e MONGODB=mongodb:27017/orderdb \
					carloselpapa10/order-service'
				}
			}
			stage('Invoice Service') {
				agent any
				steps {
					sh 'docker run -d \
					--name invoice-service \
					--network=my-net \
					-p 5002:8090 \
					--link mysql \
					--link kafka \
					--link zookeeper \
					--link cdcservice \
					-e SPRING_DATASOURCE_URL=jdbc:mysql://mysql/eventuate \
					-e SPRING_DATASOURCE_USERNAME=mysqluser \
					-e SPRING_DATASOURCE_PASSWORD=mysqlpw \
					-e SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.jdbc.Driver \
					-e SPRING_DATASOURCE_TIMEOUT=20000 \
					-e EVENTUATELOCAL_KAFKA_BOOTSTRAP_SERVERS=kafka:9092 \
					-e EVENTUATELOCAL_ZOOKEEPER_CONNECTION_STRING=zookeeper:2181 \
					-e MONGODB=mongodb:27017/invoicedb \
					carloselpapa10/invoice-service'
				}
			}
			stage('Order View Service') {
				agent any
				steps {
					sh 'docker run -d \
					--name order-view-service \
					--network=my-net \
					-p 5003:8086 \
					--link mysql \
					--link kafka \
					--link zookeeper \
					--link cdcservice \
					-e SPRING_DATASOURCE_URL=jdbc:mysql://mysql/eventuate \
					-e SPRING_DATASOURCE_USERNAME=mysqluser \
					-e SPRING_DATASOURCE_PASSWORD=mysqlpw \
					-e SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.jdbc.Driver \
					-e SPRING_DATASOURCE_TIMEOUT=20000 \
					-e EVENTUATELOCAL_KAFKA_BOOTSTRAP_SERVERS=kafka:9092 \
					-e EVENTUATELOCAL_ZOOKEEPER_CONNECTION_STRING=zookeeper:2181 \
					-e MONGODB=mongodb:27017/ordersystemdb \
					carloselpapa10/order-view-service'
				}
			}
		}
	}
	
  }
}
