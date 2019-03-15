FROM java:8
MAINTAINER c.avendano10@gmail.com
VOLUME /tmp
ADD order-view-service.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-jar","/app.jar"]
