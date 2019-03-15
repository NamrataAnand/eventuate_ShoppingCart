package com.mainclass.orderviewservice.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mainclass.orderviewservice.service.InvoiceServiceImpl;
import com.mainclass.servicemodel.invoice.api.events.InvoiceCreatedEvent;
import com.mainclass.servicemodel.invoice.api.events.InvoiceCreationFailedEvent;

import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class InvoiceHistoryEventHandlers {

	private static final Logger log = LoggerFactory.getLogger(InvoiceHistoryEventHandlers.class);
	
	@Autowired
	private InvoiceServiceImpl invoiceServiceImpl; 
	
	public DomainEventHandlers domainEventHandlers() {
	    return DomainEventHandlersBuilder
	            .forAggregateType("com.mainclass.invoiceservice.model.Invoice")
	            .onEvent(InvoiceCreatedEvent.class, this::handleInvoiceCreated)
	            .onEvent(InvoiceCreationFailedEvent.class, this::handleInvoiceCreationFailed)
	            .build();
	}
	
	public void handleInvoiceCreated(DomainEventEnvelope<InvoiceCreatedEvent> dee) {
		log.info("handleInvoiceCreated() - InvoiceHistoryEventHandlers");
		log.info("dee "+dee);
		invoiceServiceImpl.createOrderInvoice(dee.getAggregateId(), dee.getEvent().getOrderId(), dee.getEvent().getInvoiceComment());
	}
	
	public void handleInvoiceCreationFailed(DomainEventEnvelope<InvoiceCreationFailedEvent> dee) {
		log.info("handleInvoiceCreationFailed() - InvoiceHistoryEventHandlers");
		log.info("dee "+dee);
		invoiceServiceImpl.creationOrderInvoiceFailed(dee.getAggregateId());
	}
}
