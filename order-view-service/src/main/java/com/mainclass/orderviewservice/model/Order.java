package com.mainclass.orderviewservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mainclass.servicemodel.order.api.info.ProductInfo;

@Document(collection="Orders")
public class Order {

	private String id;
    private ProductInfo productInfo;
    private Invoice invoice;
    private Customer customer;
    
	public Order() {
		super();
	}

	public Order(String id, ProductInfo productInfo, Invoice invoice, Customer customer) {
		super();
		this.id = id;
		this.productInfo = productInfo;
		this.invoice = invoice;
		this.customer = customer;
	}
	

	public Order(String id, ProductInfo productInfo, Customer customer) {
		super();
		this.id = id;
		this.productInfo = productInfo;
		this.customer = customer;
	}

	public Order(String id, Invoice invoice) {
		super();
		this.id = id;
		this.invoice = invoice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	    
}
