package com.mainclass.orderservice.model;

import org.springframework.stereotype.Component;

import com.mainclass.servicemodel.order.api.events.OrderDomainEvent;

import io.eventuate.tram.events.aggregates.AbstractAggregateDomainEventPublisher;
import io.eventuate.tram.events.publisher.DomainEventPublisher;

@Component
public class OrderDomainEventPublisher extends AbstractAggregateDomainEventPublisher<Order, OrderDomainEvent>{

	public OrderDomainEventPublisher(DomainEventPublisher eventPublisher) {
	    super(eventPublisher, Order.class, Order::getId);
	}

}
