package com.mainclass.orderviewservice.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mainclass.orderviewservice.dao.CustomerService;
import com.mainclass.orderviewservice.model.Customer;
import com.mainclass.orderviewservice.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CustomerServiceImpl implements CustomerService{

	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void createCustomer(String id, String name) {
		log.info("createCustomer - CustomerServiceImpl");
		Customer customer = new Customer(id,name);
		customerRepository.save(customer);
	}

	@Override
	public Customer findCustomer(String id) {
		log.info("findCustomer - CustomerServiceImpl");
		try {
			Customer customer = customerRepository.findById(id).get();
			return customer;
		}catch (NoSuchElementException e) {
			return null;
		}
		
	}
	
	@Override
	public void updateCustomer(String id, String name) {
		log.info("updateCustomer - CustomerServiceImpl");
		Customer customer = findCustomer(id);
		if(customer!=null) {
			customer.setName(name);
			customerRepository.save(customer);
		}
	}

	@Override
	public void deleteCustomer(String id) {
		log.info("deleteCustomer - CustomerServiceImpl");
		Customer customer = findCustomer(id);
		if(customer!=null) {
			customerRepository.delete(customer);
		}
		
	}

	@Override
	public void deleteAllCustomers() {
		log.info("deleteAllCustomers - CustomerServiceImpl");
		// TODO
		
	}

	@Override
	public List<Customer> findAll() {
		log.info("findAll - CustomerServiceImpl");
		return customerRepository.findAll();
	}
}
