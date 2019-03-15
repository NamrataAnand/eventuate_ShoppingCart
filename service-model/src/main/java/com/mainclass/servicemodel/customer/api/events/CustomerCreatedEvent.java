package com.mainclass.servicemodel.customer.api.events;

public class CustomerCreatedEvent implements CustomerDomainEvent{

	private String customerId;
	private String customerName;
	
	public CustomerCreatedEvent() {
		super();
	}

	public CustomerCreatedEvent(String customerId, String customerName) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
		
}
