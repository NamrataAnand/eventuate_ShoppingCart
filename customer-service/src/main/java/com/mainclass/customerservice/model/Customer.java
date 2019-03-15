package com.mainclass.customerservice.model;

import org.springframework.data.annotation.Id;

public class Customer {

	@Id
	private String id;
	private String name;
	
	public Customer() {}
	
	public Customer(String id) {
		super();
		this.id = id;
	}
	
	public Customer(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
