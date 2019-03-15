package com.mainclass.invoiceservice.model;

import org.springframework.stereotype.Component;

import com.mainclass.servicemodel.invoice.api.events.InvoiceDomainEvent;

import io.eventuate.tram.events.aggregates.AbstractAggregateDomainEventPublisher;
import io.eventuate.tram.events.publisher.DomainEventPublisher;

@Component
public class InvoiceDomainEventPublisher extends AbstractAggregateDomainEventPublisher<Invoice,InvoiceDomainEvent>{

	public InvoiceDomainEventPublisher(DomainEventPublisher eventPublisher) {
		super(eventPublisher, Invoice.class, Invoice::getId);
	}
}
