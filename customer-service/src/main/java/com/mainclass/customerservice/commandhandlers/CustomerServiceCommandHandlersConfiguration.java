package com.mainclass.customerservice.commandhandlers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;

@Configuration
public class CustomerServiceCommandHandlersConfiguration {

	@Bean
	public CustomerServiceCommandHandlers consumerServiceCommandHandlers() {
	    return new CustomerServiceCommandHandlers();
	}
	
	@Bean
	public CommandDispatcher commandDispatcher(CustomerServiceCommandHandlers customerServiceCommandHandlers) {
	    return new SagaCommandDispatcher("customerServiceDispatcher", customerServiceCommandHandlers.commandHandlers());
	}
}
