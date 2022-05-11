package com.example.demo.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
public class OrderTable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderNo;
	private int storeId; 
	private String storeName;
	private Date dated;
	private Long orderAmount;
	private int customerId;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	public OrderTable() {
		super();
	}

	public OrderTable(int orderNo, int storeId, String storeName, Date dated, Long orderAmount, int customerId, OrderStatus orderStatus) {
		this.orderNo = orderNo;
		this.storeId = storeId;
		this.storeName = storeName;
		this.dated = dated;
		this.orderAmount = orderAmount;
		this.customerId = customerId;
		this.orderStatus = orderStatus;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Date getDated() {
		return dated;
	}

	public void setDated(Date dated) {
		this.dated = dated;
	}

	public Long getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
}
