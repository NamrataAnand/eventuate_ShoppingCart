package com.mainclass.orderviewservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Invoices")
public class Invoice {

	private String id;
	private String orderId;
    private String invoiceComment;
    
	public Invoice() {
		super();
	}

	public Invoice(String id, String orderId, String invoiceComment) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.invoiceComment = invoiceComment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
