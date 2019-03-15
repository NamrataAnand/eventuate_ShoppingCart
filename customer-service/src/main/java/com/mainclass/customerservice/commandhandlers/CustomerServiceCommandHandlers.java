package com.mainclass.customerservice.commandhandlers;

import com.mainclass.customerservice.model.Customer;
import com.mainclass.customerservice.service.CustomerService;
import com.mainclass.servicemodel.common.Constants;
import com.mainclass.servicemodel.customer.api.command.ValidateOrderByCustomer;

import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceCommandHandlers {

private static final Logger log = LoggerFactory.getLogger(CustomerServiceCommandHandlers.class);
	
	@Autowired
	private CustomerService customerService;
	
	public CommandHandlers commandHandlers() {
		return SagaCommandHandlersBuilder
	              .fromChannel(Constants.CUSTOMER_SERVICE)
	              .onMessage(ValidateOrderByCustomer.class, this::validateOrderForCustomer)
	              .build();
	}
	
	private Message validateOrderForCustomer(CommandMessage<ValidateOrderByCustomer> cm) {
		log.info("validateOrderForCustomer() - commandHandlers");
		
		Customer customer = customerService.findCustomer(cm.getCommand().getCustomerId());
		
		if(customer != null) {
			return withSuccess();
		}
		return withFailure();
	}
}
