package com.mainclass.servicemodel.customer.api.command;

import io.eventuate.tram.commands.common.Command;

public class ValidateOrderByCustomer implements Command{

	private String customerId;
	private String orderId;
	
	public ValidateOrderByCustomer() {}
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public ValidateOrderByCustomer(String customerId, String orderId) {
		super();
		this.customerId = customerId;
		this.orderId = orderId;
	}

}
