package com.mainclass.servicemodel.invoice.api.info;

public class InvoiceInfo {

	private String orderId;
	private String invoiceId;
	private String invoice;
	
	public InvoiceInfo() {}
	
	public InvoiceInfo(String orderId, String invoiceId, String invoice) {
		super();
		this.orderId = orderId;
		this.invoiceId = invoiceId;
		this.invoice = invoice;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	
}
