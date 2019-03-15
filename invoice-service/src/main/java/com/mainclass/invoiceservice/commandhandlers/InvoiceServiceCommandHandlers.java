package com.mainclass.invoiceservice.commandhandlers;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mainclass.invoiceservice.model.Invoice;
import com.mainclass.invoiceservice.service.InvoiceService;
import com.mainclass.servicemodel.common.Constants;
import com.mainclass.servicemodel.invoice.api.command.CompensateInvoiceCommand;
import com.mainclass.servicemodel.invoice.api.command.RequestInvoiceCommand;
import com.mainclass.servicemodel.invoice.api.info.InvoiceInfo;

import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

public class InvoiceServiceCommandHandlers {

	private static final Logger log = LoggerFactory.getLogger(InvoiceServiceCommandHandlers.class);

	@Autowired
	private InvoiceService invoiceService;
	
	//Constants.
	public CommandHandlers commandHandlers() {
		return SagaCommandHandlersBuilder
			    .fromChannel(Constants.INVOICE_SERVICE)
			    .onMessage(RequestInvoiceCommand.class, this::invoiceRequest)
			    .onMessage(CompensateInvoiceCommand.class, this::invoiceCompensate)
			    .build();
				
	}
	
	private Message invoiceRequest(CommandMessage<RequestInvoiceCommand> commandMessage) {
		log.info("invoiceRequest InvoiceServiceCommandHandlers");
		RequestInvoiceCommand command = commandMessage.getCommand();
		
		Invoice invoice = invoiceService.createInvoice(command.getOrderId(), command.getProductInfo());
		
		if(command.getProductInfo().getComment().equals("failure")) {
			return withFailure();
		}
		
		return withSuccess(new InvoiceInfo(invoice.getOrderId(), invoice.getId(), invoice.getInvoiceComment()));
	}
	
	private Message invoiceCompensate(CommandMessage<CompensateInvoiceCommand> commandMessage) {
		log.info("invoiceCompensate InvoiceServiceCommandHandlers");
		CompensateInvoiceCommand command = commandMessage.getCommand();
		
		invoiceService.deleteInvoice(command.getOrderId());
        return withSuccess();
	}
}
