package com.mainclass.servicemodel.order.api.info;

public class ProductInfo {

	private String productId;
	private int price;
	private String comment;
	
	public ProductInfo() {
		
	}
	public ProductInfo(String productId, int price, String comment) {
		super();
		this.productId = productId;
		this.price = price;
		this.comment = comment;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
