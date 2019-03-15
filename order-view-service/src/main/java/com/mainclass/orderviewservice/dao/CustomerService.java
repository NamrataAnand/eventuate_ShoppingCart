package com.mainclass.orderviewservice.dao;

import java.util.List;

import com.mainclass.orderviewservice.model.Customer;

public interface CustomerService {

	public void createCustomer(String id, String name);
	public Customer findCustomer(String id);
	public void updateCustomer(String id, String name);
	public void deleteCustomer(String id);
	public void deleteAllCustomers();
	public List<Customer> findAll();
	
}
