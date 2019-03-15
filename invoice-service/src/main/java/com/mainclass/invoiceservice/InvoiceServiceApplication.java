package com.mainclass.invoiceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.mainclass.invoiceservice.commandhandlers.InvoiceServiceCommandHandlersConfiguration;
import com.mainclass.invoiceservice.service.InvoiceServiceConfiguration;
import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;


@SpringBootApplication
@Import({InvoiceServiceConfiguration.class, 
		 InvoiceServiceCommandHandlersConfiguration.class,
		 TramJdbcKafkaConfiguration.class})
public class InvoiceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceServiceApplication.class, args);
	}
	
	
}
