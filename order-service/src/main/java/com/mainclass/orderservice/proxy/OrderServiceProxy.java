package com.mainclass.orderservice.proxy;

import org.springframework.stereotype.Component;

import com.mainclass.servicemodel.common.Constants;
import com.mainclass.servicemodel.order.api.command.CompleteOrderCommand;
import com.mainclass.servicemodel.order.api.command.RejectOrderCommand;

import io.eventuate.tram.commands.common.Success;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;

@Component
public class OrderServiceProxy {

	public final CommandEndpoint<RejectOrderCommand> reject = CommandEndpointBuilder
	          .forCommand(RejectOrderCommand.class)
	          .withChannel(Constants.ORDER_SERVICE)
	          .withReply(Success.class)
	.build();
	
	public final CommandEndpoint<CompleteOrderCommand> complete = CommandEndpointBuilder
	          .forCommand(CompleteOrderCommand.class)
	          .withChannel(Constants.ORDER_SERVICE)
	          .withReply(Success.class)
	.build();
}
