package com.mainclass.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import com.mainclass.orderservice.commandhandlers.OrderServiceCommandHandlersConfiguration;
import com.mainclass.orderservice.service.OrderServiceConfiguration;
import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;

@EnableAutoConfiguration
@Import({OrderServiceCommandHandlersConfiguration.class,
		 OrderServiceConfiguration.class,
		 TramJdbcKafkaConfiguration.class
    	})
@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
	    SpringApplication.run(OrderServiceApplication.class, args);
	}
	
}
