package com.example.demo.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OrderItems {
	
	@Id
	@GeneratedValue
	private int orderItemId;
	private int orderNo;
	private int productId;
	private int quantity;
	private int price;
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy = "orderItems")
	@JoinColumn(name="prodId")
	private Product product;
	
	public OrderItems() {
		super();
	}

	public OrderItems(int orderNo, int productId, int quantity, int price, OrderStatus status) {
		super();
		this.orderNo = orderNo;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
		this.status = status;
	}
	
	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
