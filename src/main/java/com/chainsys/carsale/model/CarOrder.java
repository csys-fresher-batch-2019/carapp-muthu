package com.chainsys.carsale.model;

import java.sql.Date;

public class CarOrder {
	private int orderId;
	private String BuyerName;
	private long BuyerContactNo;
	private int carId;
	private int sellerId;
	private String testDrive;
	private String status;
private int userId;
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	private Date orderedDate;
	private Date deliveredDate;
	private String address1;
	private String address2;
	private int pincode;
	private String BuyerState;
	private String city;

	public int getOrderId() {
		return orderId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getBuyerState() {
		return BuyerState;
	}

	public void setBuyerState(String buyerState) {
		BuyerState = buyerState;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	private String carName;

	public String getBuyerName() {
		return BuyerName;
	}

	public void setBuyerName(String buyerName) {
		BuyerName = buyerName;
	}

	public long getBuyerContactNo() {
		return BuyerContactNo;
	}

	public void setBuyerContactNo(long buyerContactNo) {
		BuyerContactNo = buyerContactNo;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getTestDrive() {
		return testDrive;
	}

	public void setTestDrive(String testDrive) {
		this.testDrive = testDrive;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}

	public Date getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	@Override
	public String toString() {
		return "CarOrder [orderId=" + orderId + ", BuyerName=" + BuyerName + ", BuyerContactNo=" + BuyerContactNo
				+ ", carId=" + carId + ", sellerId=" + sellerId + ", testDrive=" + testDrive + ", status=" + status
				+ ", userId=" + userId + ", orderedDate=" + orderedDate + ", deliveredDate=" + deliveredDate
				+ ", address1=" + address1 + ", address2=" + address2 + ", pincode=" + pincode + ", BuyerState="
				+ BuyerState + ", city=" + city + ", carName=" + carName + "]";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
