package com.mainclass.servicemodel.order.api.events;

public class OrderRejectedEvent implements OrderDomainEvent{

	private String orderId;

	public OrderRejectedEvent() {
		super();
	}

	public OrderRejectedEvent(String orderId) {
		super();
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
