package com.mainclass.orderviewservice.dao;

import java.util.List;

import com.mainclass.orderviewservice.model.Customer;
import com.mainclass.orderviewservice.model.Invoice;
import com.mainclass.orderviewservice.model.Order;
import com.mainclass.servicemodel.order.api.info.ProductInfo;

public interface OrderService {

	public Order createOrder(String orderId, ProductInfo productInfo, Customer customer);
	public Order completeCreateOrder(String orderId, Invoice invoice);
	public Order findOrder(String orderId);
	public List<Order> findAllOrders();
	public void deleteOrder(String orderId);
}
