package com.mainclass.customerservice.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mainclass.customerservice.model.Customer;
import com.mainclass.customerservice.model.CustomerDomainEventPublisher;
import com.mainclass.customerservice.model.CustomerRepository;
import com.mainclass.servicemodel.customer.api.events.CustomerCreatedEvent;
import com.mainclass.servicemodel.customer.api.events.CustomerDeletedEvent;
import com.mainclass.servicemodel.customer.api.events.CustomerDomainEvent;
import com.mainclass.servicemodel.customer.api.events.CustomerUpdatedEvent;

import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;
import static java.util.Collections.singletonList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class CustomerService {

	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerDomainEventPublisher customerAggregateEventPublisher;
	
	public Customer createCustomer(String id, String name) {
		log.info("createCustomer - CustomerService");
		
		List<CustomerDomainEvent> events = singletonList(new CustomerCreatedEvent(id, name));
		ResultWithDomainEvents<Customer,CustomerDomainEvent> customerANdEvents = new ResultWithDomainEvents<>(new Customer(id, name),events);
		
		Customer customer = customerRepository.save(new Customer(id, name));
		customerAggregateEventPublisher.publish(customer, customerANdEvents.events);
		return customer;
	}
	
	public Customer findCustomer(String customerId) {		
		log.info("findCustomer - CustomerService");
		try {
			Customer customer = customerRepository.findById(customerId).get();
			return customer;
		}catch (NoSuchElementException e) {
			return null;
		}		
	}
	
	public List<Customer> findAll(){
		log.info("findAll - CustomerService");
		return customerRepository.findAll();
	}
	
	public Customer updateCustomer(String id, String name) {
		log.info("updateCustomer - CustomerService");
		Customer customer = findCustomer(id);
		if(customer!=null) {
			
			List<CustomerDomainEvent> events = singletonList(new CustomerUpdatedEvent(id, name));
			ResultWithDomainEvents<Customer,CustomerDomainEvent> customerANdEvents = new ResultWithDomainEvents<>(new Customer(id, name),events);
			
			customer.setName(name);
			customerRepository.save(customer);
			customerAggregateEventPublisher.publish(customer, customerANdEvents.events);
			
			return customer;
		}
		return null;
	}
	
	public void deleteCustomer(String id) {
		log.info("deleteCustomer - CustomerService");
		Customer customer = findCustomer(id);
		if(customer!=null) {
			
			List<CustomerDomainEvent> events = singletonList(new CustomerDeletedEvent(id));
			ResultWithDomainEvents<Customer,CustomerDomainEvent> customerANdEvents = new ResultWithDomainEvents<>(new Customer(id),events);
			
			customerRepository.delete(customer);
			customerAggregateEventPublisher.publish(customer, customerANdEvents.events);
		}		
	}
	
	public void deleteAll() {		
		/*List<CustomerDomainEvent> events = singletonList(new CustomerDeletedAllEvent());
		ResultWithDomainEvents<Customer,CustomerDomainEvent> customerANdEvents = new ResultWithDomainEvents<>(new Customer(),events);		
		customerRepository.deleteAll();
		customerAggregateEventPublisher.publish( new Customer(), customerANdEvents.events);*/
	}
}
