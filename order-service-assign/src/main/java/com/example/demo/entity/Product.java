package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Product {
	
	@Id
	private int prodId;
	private String productName;
	private int rating;
	private int price;
	private boolean isActive;
	
	@OneToOne
	@JoinColumn(name="productId")
	private OrderItems orderItems;
	
//	  @OneToOne(fetch = FetchType.LAZY)
//	    @JoinColumn(name = "user_id", nullable = false)
//	    private User user;
	
	public Product() {
		super();
	}

	public Product(int prodId, String productName, int rating, int price, boolean isActive) {
		super();
		this.prodId = prodId;
		this.productName = productName;
		this.rating = rating;
		this.price = price;
		this.isActive = isActive;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public OrderItems getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(OrderItems orderItems) {
		this.orderItems = orderItems;
	}
	
	

}
