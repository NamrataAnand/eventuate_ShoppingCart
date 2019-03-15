package com.mainclass.orderservice.commandhandlers;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.mainclass.orderservice.model.Order;
import com.mainclass.orderservice.model.OrderDomainEventPublisher;
import com.mainclass.orderservice.model.OrderRepository;
import com.mainclass.orderservice.saga.createorder.CreateOrderSaga;
import com.mainclass.orderservice.service.OrderService;
import com.mainclass.servicemodel.common.Constants;
import com.mainclass.servicemodel.order.api.command.CompleteOrderCommand;
import com.mainclass.servicemodel.order.api.command.RejectOrderCommand;
import com.mainclass.servicemodel.order.api.events.OrderCompletedEvent;
import com.mainclass.servicemodel.order.api.events.OrderDomainEvent;
import com.mainclass.servicemodel.order.api.events.OrderRejectedEvent;

import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import static java.util.Collections.singletonList;

public class OrderServiceCommandHandlers {

	private static final Logger log = LoggerFactory.getLogger(CreateOrderSaga.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderDomainEventPublisher orderDomainEventPublisher;
	
	public CommandHandlers commandHandlers() {
		return SagaCommandHandlersBuilder
				.fromChannel(Constants.ORDER_SERVICE)				
				.onMessage(CompleteOrderCommand.class, this::completeOrder)
				.onMessage(RejectOrderCommand.class, this::rejectOrder)
				.build();
	}
	
	public Message completeOrder(CommandMessage<CompleteOrderCommand> cm) {
		log.info("completeOrder() - OrderCommandHandlers");
		
		CompleteOrderCommand command = cm.getCommand();
		Order order = orderService.findOrder(command.getOrderId());
		
		if(order==null) {
			return withFailure();
		}
		
		log.info("order completed successfully. orderId= "+order.getId());
		
		order.setCompleted(true);
		
		List<OrderDomainEvent> events = singletonList(new OrderCompletedEvent(order.getId(),order.getProductInfo(),order.getInvoiceId(),order.getCustomerId()));
		ResultWithDomainEvents<Order,OrderDomainEvent> orderAndEvents = new ResultWithDomainEvents<>(new Order(order.getId(),order.getProductInfo(),order.getInvoiceId(),order.getCustomerId()),events); 
				
		orderRepository.save(order);
		orderDomainEventPublisher.publish(order, orderAndEvents.events);
		
		return withSuccess();
	}
	
	public Message rejectOrder(CommandMessage<RejectOrderCommand> cm) {
		log.info("rejectOrder() - OrderCommandHandlers");
		
		RejectOrderCommand command = cm.getCommand();
		Order order = orderService.findOrder(command.getOrderId());
		
		if(order==null) {
			return withFailure();
		}
		
		log.info("order rejected successfully. orderId= "+order.getId());
		
		order.setCompleted(false);
		
		List<OrderDomainEvent> events = singletonList(new OrderRejectedEvent(order.getId()));
		ResultWithDomainEvents<Order,OrderDomainEvent> orderAndEvents = new ResultWithDomainEvents<>(new Order(order.getId()),events);
		
		orderRepository.save(order);
		orderDomainEventPublisher.publish(order, orderAndEvents.events);
		
		return withSuccess();
	}
}
