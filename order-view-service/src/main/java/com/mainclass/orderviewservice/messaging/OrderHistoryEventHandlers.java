package com.mainclass.orderviewservice.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;

import com.mainclass.orderviewservice.model.Customer;
import com.mainclass.orderviewservice.model.Invoice;
import com.mainclass.orderviewservice.service.CustomerServiceImpl;
import com.mainclass.orderviewservice.service.InvoiceServiceImpl;
import com.mainclass.orderviewservice.service.OrderServiceImpl;
import com.mainclass.servicemodel.order.api.events.OrderCompletedEvent;
import com.mainclass.servicemodel.order.api.events.OrderCreatedEvent;
import com.mainclass.servicemodel.order.api.events.OrderRejectedEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class OrderHistoryEventHandlers {

	private static final Logger log = LoggerFactory.getLogger(OrderHistoryEventHandlers.class);
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	private InvoiceServiceImpl invoiceServiceImpl; 
	
	public DomainEventHandlers domainEventHandlers() {
		    return DomainEventHandlersBuilder
		            .forAggregateType("com.mainclass.orderservice.model.Order")
		            .onEvent(OrderCreatedEvent.class, this::handleOrderCreated)
		            .onEvent(OrderCompletedEvent.class, this::handleOrderCompleted)
		            .onEvent(OrderRejectedEvent.class, this::handleOrderRejected)
		            .build();
	}
	 
	public void handleOrderCreated(DomainEventEnvelope<OrderCreatedEvent> dee) {
		log.info("handleOrderCreated() - OrderHistoryEventHandlers");
		
		Customer customer = customerServiceImpl.findCustomer(dee.getEvent().getCustomerId());
		orderServiceImpl.createOrder(dee.getAggregateId(), dee.getEvent().getProductInfo(), customer);
	}
	 
	public void handleOrderCompleted(DomainEventEnvelope<OrderCompletedEvent> dee) {
		log.info("handleOrderCompleted() - OrderHistoryEventHandlers");
		
		Invoice invoice = invoiceServiceImpl.findInvoice(dee.getEvent().getInvoiceId());		
		orderServiceImpl.completeCreateOrder(dee.getAggregateId(), invoice);		
	}
	
	public void handleOrderRejected(DomainEventEnvelope<OrderRejectedEvent> dee) {
		log.info("handleOrderRejected() - OrderHistoryEventHandlers");
		
		orderServiceImpl.deleteOrder(dee.getAggregateId());
	}
}
