package com.mainclass.customerservice.webapi;

public class CreateCustomerRequest {

	private String id;
	private String name;
	
	public CreateCustomerRequest() {
	}

	public CreateCustomerRequest(String id, String name) {
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
