package com.mainclass.servicemodel.invoice.api.command;

import com.mainclass.servicemodel.order.api.info.ProductInfo;

import io.eventuate.tram.commands.common.Command;

public class RequestInvoiceCommand implements Command{

	private String orderId;
	private ProductInfo productInfo;
	
	public RequestInvoiceCommand() {}
	
	public RequestInvoiceCommand(String orderId, ProductInfo productInfo) {
		super();
		this.orderId = orderId;
		this.productInfo = productInfo;
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
		
}
