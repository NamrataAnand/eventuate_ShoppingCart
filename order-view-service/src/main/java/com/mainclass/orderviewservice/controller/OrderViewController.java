package com.mainclass.orderviewservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mainclass.orderviewservice.model.Customer;
import com.mainclass.orderviewservice.model.Invoice;
import com.mainclass.orderviewservice.model.Order;
import com.mainclass.orderviewservice.service.CustomerServiceImpl;
import com.mainclass.orderviewservice.service.InvoiceServiceImpl;
import com.mainclass.orderviewservice.service.OrderServiceImpl;

@RestController
public class OrderViewController {

	@Autowired
	private CustomerServiceImpl customerServiceImpl; 
	
	@Autowired
	private InvoiceServiceImpl invoiceServiceImpl;
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@GetMapping("customers")
	public List<Customer> findAllCustomers(){
		return customerServiceImpl.findAll();
	}
	
	@GetMapping("customer/{customerId}")
	public Customer findCustomer(@PathVariable String customerId) {
		return customerServiceImpl.findCustomer(customerId);
	}
	
	@GetMapping("invoices")
	public List<Invoice> findAllInvoices(){
		return invoiceServiceImpl.findAll();
	}
	
	@GetMapping("invoices/{invoiceId}")
	public Invoice findInvoice(@PathVariable String invoiceId) {
		return invoiceServiceImpl.findInvoice(invoiceId);
	}
	
	@GetMapping("orders")
	public List<Order> findAllOrders() {
		return orderServiceImpl.findAllOrders();
	}
	
	@GetMapping("orders/{orderId}")
	public Order findOrder(@PathVariable String orderId) {
		return orderServiceImpl.findOrder(orderId);
	}
}
