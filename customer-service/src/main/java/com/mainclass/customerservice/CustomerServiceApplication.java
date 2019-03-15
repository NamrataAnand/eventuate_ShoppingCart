package com.mainclass.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.mainclass.customerservice.commandhandlers.CustomerServiceCommandHandlersConfiguration;
import com.mainclass.customerservice.service.CustomerServiceConfiguration;

import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;

@SpringBootApplication
@Import({CustomerServiceConfiguration.class, 
		 CustomerServiceCommandHandlersConfiguration.class,
		 TramJdbcKafkaConfiguration.class})
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
}
