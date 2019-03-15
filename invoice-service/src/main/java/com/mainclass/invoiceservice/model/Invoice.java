package com.mainclass.invoiceservice.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
//@NoArgsConstructor
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
	
    private String orderId;
    private String invoiceComment;
	
    public Invoice() {}
    
    public Invoice(String orderId, String invoiceComment) {
		super();
		this.orderId = orderId;
		this.invoiceComment = invoiceComment;
	}

	public Invoice(String id) {
		super();
		this.id = id;
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
