package com.mainclass.orderservice.webapi;

public class CreateOrderResponse {

	private String orderId;

	public CreateOrderResponse() {}
	public CreateOrderResponse(String orderId) {
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
