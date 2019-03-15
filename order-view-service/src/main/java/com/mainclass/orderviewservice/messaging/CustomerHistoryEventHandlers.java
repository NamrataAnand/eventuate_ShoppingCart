package com.mainclass.orderviewservice.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mainclass.orderviewservice.service.CustomerServiceImpl;
import com.mainclass.servicemodel.customer.api.events.CustomerCreatedEvent;
import com.mainclass.servicemodel.customer.api.events.CustomerDeletedAllEvent;
import com.mainclass.servicemodel.customer.api.events.CustomerDeletedEvent;
import com.mainclass.servicemodel.customer.api.events.CustomerUpdatedEvent;

import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CustomerHistoryEventHandlers {

	private static final Logger log = LoggerFactory.getLogger(CustomerHistoryEventHandlers.class);
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl; 
	
	public DomainEventHandlers domainEventHandlers() {
	    return DomainEventHandlersBuilder
	            .forAggregateType("com.mainclass.customerservice.model.Customer")
	            .onEvent(CustomerCreatedEvent.class, this::handleCustomerCreated)
	            .onEvent(CustomerUpdatedEvent.class, this::handleCustomerUpdated)
	            .onEvent(CustomerDeletedEvent.class, this::handleCustomerDeleted)
	            .onEvent(CustomerDeletedAllEvent.class, this::handleCustomerDeletedAll)
	            .build();
	}
	
	public void handleCustomerCreated(DomainEventEnvelope<CustomerCreatedEvent> dee) {
		log.info("handleCustomerCreated() - CustomerHistoryEventHandlers");
		log.info("dee "+dee);
		customerServiceImpl.createCustomer(dee.getAggregateId(), dee.getEvent().getCustomerName());
	}
	
	public void handleCustomerUpdated(DomainEventEnvelope<CustomerUpdatedEvent> dee) {
		log.info("handleCustomerUpdated() - CustomerHistoryEventHandlers");
		log.info("dee "+dee);
		customerServiceImpl.updateCustomer(dee.getAggregateId(), dee.getEvent().getCustomerName());
	}
	
	public void handleCustomerDeleted(DomainEventEnvelope<CustomerDeletedEvent> dee) {
		log.info("handleCustomerDeleted() - CustomerHistoryEventHandlers");
		log.info("dee "+dee);
		customerServiceImpl.deleteCustomer(dee.getAggregateId());
	}
	
	public void handleCustomerDeletedAll(DomainEventEnvelope<CustomerDeletedAllEvent> dee) {
		log.info("handleCustomerDeletedAll() - CustomerHistoryEventHandlers");
		log.info("dee "+dee);
		/*TODO*/
	}
}
