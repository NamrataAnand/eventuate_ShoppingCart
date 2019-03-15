package com.mainclass.customerservice.model;

import org.springframework.stereotype.Component;

import io.eventuate.tram.events.aggregates.AbstractAggregateDomainEventPublisher;
import io.eventuate.tram.events.publisher.DomainEventPublisher;

import com.mainclass.servicemodel.customer.api.events.CustomerDomainEvent;

@Component
public class CustomerDomainEventPublisher extends AbstractAggregateDomainEventPublisher<Customer,CustomerDomainEvent>{

	public CustomerDomainEventPublisher(DomainEventPublisher eventPublisher) {
		super(eventPublisher, Customer.class, Customer::getId);
	}

}
