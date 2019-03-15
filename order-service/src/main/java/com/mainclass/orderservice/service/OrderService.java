package com.mainclass.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.eventuate.tram.sagas.orchestration.SagaManager;
import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Collections.singletonList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mainclass.orderservice.model.Order;
import com.mainclass.orderservice.model.OrderDomainEventPublisher;
import com.mainclass.orderservice.model.OrderRepository;
import com.mainclass.orderservice.saga.createorder.CreateOrderSagaData;
import com.mainclass.servicemodel.order.api.events.OrderCreatedEvent;
import com.mainclass.servicemodel.order.api.events.OrderDomainEvent;
import com.mainclass.servicemodel.order.api.info.ProductInfo;

@Component
@Transactional
public class OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private SagaManager<CreateOrderSagaData> createOrderSagaManager;
	
	@Autowired
	private OrderDomainEventPublisher orderAggregateEventPublisher;
	
	public Order createOrder(ProductInfo productInfo, String customerId) {
		log.info("processing order for " + productInfo);		
				
		List<OrderDomainEvent> events = singletonList(new OrderCreatedEvent(productInfo, customerId));		
		ResultWithDomainEvents<Order, OrderDomainEvent> orderAndEvents = new ResultWithDomainEvents<>(new Order(productInfo, customerId), events);
		
		Order order = orderRepository.save((Order)orderAndEvents.result);
		
		orderAggregateEventPublisher.publish(order, orderAndEvents.events);
		
		CreateOrderSagaData data = new CreateOrderSagaData(order.getId(), customerId, productInfo); 
		createOrderSagaManager.create(data, Order.class, order.getId());
		
		return order;
	}
	
	public Order findOrder(String orderId) {
		try {
			Order order = orderRepository.findById(orderId).get();
			return order;
		}catch (NoSuchElementException e) {
			return null;
		}
	}
	
	public List<Order> findAll(){
		return orderRepository.findAll();
	}
	
	public void deleteAll() {
		orderRepository.deleteAll();
	}
}
