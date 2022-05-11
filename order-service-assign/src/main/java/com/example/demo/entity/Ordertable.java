package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ordertable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderNo;
	private int storeId;
	private String storeName;
	private String storeAddress;
	private Date dated;
	private Long orderAmount;
	private String custEmail;
	private int custId;
	private String orderStatus;
	private Date lastUpdate;
	private String channel;
	
	
	
	public Ordertable() {
		super();
	}

	public Ordertable(int orderNo,int storeId, String storeName, String storeAddress, Date date, Long orderAmount, String custEmail,
			int custId, String orderStatus, Date lastUpdate, String channel) {
		super();
		this.orderNo=orderNo;
		this.storeId = storeId;
		this.storeName = storeName;
		this.storeAddress = storeAddress;
		this.dated = date;
		this.orderAmount = orderAmount;
		this.custEmail = custEmail;
		this.custId = custId;
		this.orderStatus = orderStatus;
		this.lastUpdate = lastUpdate;
		this.channel = channel;
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
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public Date getDate() {
		return dated;
	}
	public void setDate(Date date) {
		this.dated = date;
	}
	public Long getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
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
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
