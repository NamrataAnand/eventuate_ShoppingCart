package com.mainclass.servicemodel.invoice.api.events;

public class InvoiceCreatedEvent implements InvoiceDomainEvent{

	private String orderId;
	private String invoiceComment;
	
	public InvoiceCreatedEvent() {
	}

	public InvoiceCreatedEvent(String orderId, String invoiceComment) {
		super();
		this.orderId = orderId;
		this.invoiceComment = invoiceComment;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getInvoiceComment() {
		return invoiceComment;
	}

	public void setInvoiceComment(String invoiceComment) {
		this.invoiceComment = invoiceComment;
	}
		
}
