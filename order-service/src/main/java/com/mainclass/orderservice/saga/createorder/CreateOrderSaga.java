package com.mainclass.orderservice.saga.createorder;

import org.springframework.stereotype.Component;
import com.mainclass.orderservice.model.Order;
import com.mainclass.orderservice.model.OrderRepository;
import com.mainclass.orderservice.proxy.CustomerServiceProxy;
import com.mainclass.orderservice.proxy.InvoiceServiceProxy;
import com.mainclass.orderservice.proxy.OrderServiceProxy;
import com.mainclass.orderservice.service.OrderService;
import com.mainclass.servicemodel.customer.api.command.ValidateOrderByCustomer;
import com.mainclass.servicemodel.invoice.api.command.CompensateInvoiceCommand;
import com.mainclass.servicemodel.invoice.api.command.RequestInvoiceCommand;
import com.mainclass.servicemodel.invoice.api.info.InvoiceInfo;
import com.mainclass.servicemodel.order.api.command.CompleteOrderCommand;
import com.mainclass.servicemodel.order.api.command.RejectOrderCommand;

import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CreateOrderSaga implements SimpleSaga<CreateOrderSagaData>{

	private static final Logger log = LoggerFactory.getLogger(CreateOrderSaga.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRepository orderRepository; 
	
	private SagaDefinition<CreateOrderSagaData> sagaDefinition;
	
	public CreateOrderSaga(OrderServiceProxy orderService, CustomerServiceProxy customerService, InvoiceServiceProxy invoiceService) {
		this.sagaDefinition =
				step()
					.withCompensation(orderService.reject, this::makeRejectOrderCommand)
				.step()
					.invokeParticipant(customerService.validateOrder, this::makeValidateOrderByCustomer)						
				.step()
					.invokeParticipant(invoiceService.requestInvoice, this::makeRequestInvoice)
					.onReply(InvoiceInfo.class, this::handleInvoiceReply)
					.withCompensation(invoiceService.cancelInvoice, this::makeCancelRequestInvoice)
				.step()
					.invokeParticipant(orderService.complete, this::makeCompleteOrderCommand)	
				.build();
	}
	@Override
	public SagaDefinition<CreateOrderSagaData> getSagaDefinition() {
		return sagaDefinition;
	}
	
	private ValidateOrderByCustomer makeValidateOrderByCustomer(CreateOrderSagaData data) {
		log.info("makeValidateOrderByCustomer() - CreateOrderSaga");
		return new ValidateOrderByCustomer(data.getCustomerId(),data.getOrderId());
	}
	
	private RequestInvoiceCommand makeRequestInvoice(CreateOrderSagaData data) {
		log.info("makeRequestInvoice() - CreateOrderSaga");
		return new RequestInvoiceCommand(data.getOrderId(), data.getProductInfo());
	}
	
	private void handleInvoiceReply(CreateOrderSagaData data, InvoiceInfo invoiceInfo) {
		log.info("makeValidateOrderByCustomer() - CreateOrderSaga");
		Order order = orderService.findOrder(data.getOrderId());
		
		if(order==null) {log.info("FAILURE ==> handleInvoiceReply - CreateOrderSaga");}
		
		order.setInvoiceId(invoiceInfo.getInvoiceId());
		orderRepository.save(order);
		log.info("order updated with invoice - " + order);		
	}
	
	private CompensateInvoiceCommand makeCancelRequestInvoice(CreateOrderSagaData data) {
		return new CompensateInvoiceCommand(data.getOrderId());
	}
	
	private RejectOrderCommand makeRejectOrderCommand(CreateOrderSagaData data) {
		log.info("makeRejectOrderCommand() - CreateOrderSaga");
		return new RejectOrderCommand(data.getOrderId());
	}
	
	private CompleteOrderCommand makeCompleteOrderCommand(CreateOrderSagaData data) {
		log.info("makeCompleteOrderCommand() - CreateOrderSaga");
		return new CompleteOrderCommand(data.getOrderId());
	}

}
