package com.mainclass.servicemodel.invoice.api.events;


public class InvoiceCreationFailedEvent implements InvoiceDomainEvent{

	private String orderId;

	
	public InvoiceCreationFailedEvent() {
	}
	
	public InvoiceCreationFailedEvent(String orderId) {
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
