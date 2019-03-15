package com.mainclass.orderservice.proxy;

import org.springframework.stereotype.Component;

import com.mainclass.servicemodel.common.Constants;
import com.mainclass.servicemodel.invoice.api.command.CompensateInvoiceCommand;
import com.mainclass.servicemodel.invoice.api.command.RequestInvoiceCommand;
import com.mainclass.servicemodel.invoice.api.info.InvoiceInfo;

import io.eventuate.tram.commands.common.Success;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;

@Component
public class InvoiceServiceProxy {

	public final CommandEndpoint<RequestInvoiceCommand> requestInvoice= CommandEndpointBuilder
	          .forCommand(RequestInvoiceCommand.class)
	          .withChannel(Constants.INVOICE_SERVICE)
	          .withReply(InvoiceInfo.class)
	          .build();
	
	public final CommandEndpoint<CompensateInvoiceCommand> cancelInvoice= CommandEndpointBuilder
	          .forCommand(CompensateInvoiceCommand.class)
	          .withChannel(Constants.INVOICE_SERVICE)
	          .withReply(Success.class)
	          .build();
	
}
