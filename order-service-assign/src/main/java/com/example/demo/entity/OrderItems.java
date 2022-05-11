package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class OrderItems {
	
	@Id
	@GeneratedValue
	private int orderItemId;
	private int orderNo;
	private int productId;
	private int quantity;
	private int price;
	private String status;
	private String orderStatus;
	private Date lastUpdate;
	private String channel;
	
	
	
	public OrderItems() {
		super();
	}

	public OrderItems(int orderNo, int productId, int quantity, int price, String status, String orderStatus,
			Date lastUpdate, String channel) {
		super();
		this.orderNo = orderNo;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
		this.status = status;
		this.orderStatus = orderStatus;
		this.lastUpdate = lastUpdate;
		this.channel = channel;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	
}
