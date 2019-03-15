package com.mainclass.orderviewservice.messaging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.messaging.consumer.MessageConsumer;

@Configuration
public class EventHandlersConfiguration {

	@Bean
	public DomainEventDispatcher orderHistoryDomainEventDispatcher(OrderHistoryEventHandlers orderHistoryEventHandlers, MessageConsumer messageConsumer) {
	    return new DomainEventDispatcher("orderHistoryDomainEventDispatcher", orderHistoryEventHandlers.domainEventHandlers(), messageConsumer);
	}
	
	@Bean
	public DomainEventDispatcher customerHistoryDomainEventDispatcher(CustomerHistoryEventHandlers customerHistoryEventHandlers, MessageConsumer messageConsumer) {
		return new DomainEventDispatcher("customerHistoryDomainEventDispatcher", customerHistoryEventHandlers.domainEventHandlers(), messageConsumer);
	}
	
	@Bean
	public DomainEventDispatcher invoiceHistoryDomainEventDispatcher(InvoiceHistoryEventHandlers invoiceHistoryEventHandlers, MessageConsumer messageConsumer ) {
		return new DomainEventDispatcher("invoiceHistoryDomainEventDispatcher", invoiceHistoryEventHandlers.domainEventHandlers(), messageConsumer);
	}
}
