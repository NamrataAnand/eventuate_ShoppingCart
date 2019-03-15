package com.mainclass.orderviewservice.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.mainclass.orderviewservice.dao.OrderService;
import com.mainclass.orderviewservice.model.Customer;
import com.mainclass.orderviewservice.model.Invoice;
import com.mainclass.orderviewservice.model.Order;
import com.mainclass.orderviewservice.repository.OrderRepository;
import com.mainclass.servicemodel.order.api.info.ProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderRepository orderRepository;	
	
	@Override
	public Order createOrder(String orderId, ProductInfo productInfo, Customer customer) {
		log.info("createOrder - OrderServiceImpl");
		Order order = new Order(orderId, productInfo, customer);
		return orderRepository.save(order);
	}
	
	@Override
	public Order completeCreateOrder(String orderId, Invoice invoice) {
		log.info("createOrder - OrderServiceImpl");
		Order order = findOrder(orderId);
		
		if(order != null) {
			order.setInvoice(invoice);
			return orderRepository.save(order);
		}
		
		log.info("completeCreateOrder - OrderServiceImpl ::: Unexpected error");
		return null;
		
	}

	@Override
	public Order findOrder(String orderId) {
		log.info("findOrder - OrderServiceImpl");
		
		try {
			Order order = orderRepository.findById(orderId).get();
			return order;
		}catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public List<Order> findAllOrders() {
		log.info("findAllOrders - OrderServiceImpl");
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(String orderId) {
		log.info("deleteOrder - OrderServiceImpl");
		
		Order order = findOrder(orderId);
		
		if(order != null) {
			orderRepository.delete(order);
		}else {
			log.info("deleteOrder - OrderServiceImpl ::: Unexpected error");
		}
		
	}

}
