package com.mainclass.invoiceservice.commandhandlers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;

@Configuration
public class InvoiceServiceCommandHandlersConfiguration {

	@Bean
	public InvoiceServiceCommandHandlers invoiceServiceCommandHandlers() {
	    return new InvoiceServiceCommandHandlers();
	}
	
	@Bean
	public CommandDispatcher commandDispatcher(InvoiceServiceCommandHandlers invoiceServiceCommandHandlers) {
	    return new SagaCommandDispatcher("invoiceServiceDispatcher", invoiceServiceCommandHandlers.commandHandlers());
	}
}
