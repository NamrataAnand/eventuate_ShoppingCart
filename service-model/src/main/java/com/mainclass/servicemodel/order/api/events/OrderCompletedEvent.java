package com.mainclass.servicemodel.order.api.events;

import com.mainclass.servicemodel.order.api.info.ProductInfo;

public class OrderCompletedEvent implements OrderDomainEvent{

	private String orderId;
    private ProductInfo productInfo;
    private String invoiceId;
    private String customerId;
    
	public OrderCompletedEvent() {
		super();
	}
	
	public OrderCompletedEvent(String orderId, ProductInfo productInfo, String invoiceId, String customerId) {
		super();
		this.orderId = orderId;
		this.productInfo = productInfo;
		this.invoiceId = invoiceId;
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
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
    
}
