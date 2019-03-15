package com.mainclass.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mainclass.orderservice.model.Order;
import com.mainclass.orderservice.service.OrderService;
import com.mainclass.orderservice.webapi.CreateOrderRequest;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
		
	
	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	 public String createOrder(@RequestBody CreateOrderRequest request) {
		Order order = orderService.createOrder(request.getProductInfo(), request.getCustomerId());
		
	    return "Order is being processed - " + order.getId();
	}
	
	@GetMapping("/orders")
	public List<Order> getAllOrders() {
	        return orderService.findAll();
	}
	
	@GetMapping("/order/{orderId}")
    public ResponseEntity<Order> findOne(@PathVariable String orderId) {
        Order order = orderService.findOrder(orderId);        
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
	
	@DeleteMapping("/deleteOrdersAll")
	public String deleteOrders() {
		orderService.deleteAll();
		return "Orders deleted";
	}
}
