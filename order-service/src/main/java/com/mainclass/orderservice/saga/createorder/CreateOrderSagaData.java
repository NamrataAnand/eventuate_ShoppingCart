package com.mainclass.orderservice.saga.createorder;

import com.mainclass.servicemodel.order.api.info.ProductInfo;

public class CreateOrderSagaData {

	private String orderId;
	private String customerId;
	private ProductInfo productInfo;
	
	public CreateOrderSagaData() {}
	
	public CreateOrderSagaData(String orderId, String customerId, ProductInfo productInfo) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.productInfo = productInfo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
	
}
