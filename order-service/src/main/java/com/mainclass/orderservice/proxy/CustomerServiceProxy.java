package com.mainclass.orderservice.proxy;

import org.springframework.stereotype.Component;

import com.mainclass.servicemodel.common.Constants;
import com.mainclass.servicemodel.customer.api.command.ValidateOrderByCustomer;

import io.eventuate.tram.commands.common.Success;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;

@Component
public class CustomerServiceProxy {

	public final CommandEndpoint<ValidateOrderByCustomer> validateOrder= CommandEndpointBuilder
	          .forCommand(ValidateOrderByCustomer.class)
	          .withChannel(Constants.CUSTOMER_SERVICE)
	          .withReply(Success.class)
	          .build();
}
