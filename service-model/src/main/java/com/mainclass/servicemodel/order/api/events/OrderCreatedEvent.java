package com.mainclass.servicemodel.order.api.events;

import com.mainclass.servicemodel.order.api.info.ProductInfo;

public class OrderCreatedEvent implements OrderDomainEvent{

	private String orderId;
	private ProductInfo productInfo;
	private String customerId;
	
	public OrderCreatedEvent() {
		super();
	}

	public OrderCreatedEvent(String orderId) {
		super();
		this.orderId = orderId;
	}
	
	
	public OrderCreatedEvent(String orderId, ProductInfo productInfo, String customerId) {
		super();
		this.orderId = orderId;
		this.productInfo = productInfo;
		this.customerId = customerId;
	}

	public OrderCreatedEvent(ProductInfo productInfo, String customerId) {
		super();
		this.productInfo = productInfo;
		this.customerId = customerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}
