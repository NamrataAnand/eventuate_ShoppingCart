package com.mainclass.servicemodel.customer.api.events;

public class CustomerDeletedEvent implements CustomerDomainEvent{

	private String customerId;

	public CustomerDeletedEvent(String customerId) {
		super();
		this.customerId = customerId;
	}

	public CustomerDeletedEvent() {
		super();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}
