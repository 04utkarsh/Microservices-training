package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;

@Entity
public class OrderTable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderNo;
	@Min(value = 1,message="storeId cant be blank")
	private int storeId; 
	private String storeName;
	private Date dated;
	private Long orderAmount;
	@Min(value = 1,message="customerId cant be blank")
	private int customerId;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="orderNo")
	private List<OrderItems> orderItemsList;
	
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

	public List<OrderItems> getOrderItemsList() {
		return orderItemsList;
	}

	public void setOrderItemsList(List<OrderItems> orderItemsList) {
		this.orderItemsList = orderItemsList;
	}
	
	public void addSingleOrderItems(OrderItems orderItems) {
		this.orderItemsList.add(orderItems);
	}
	
}