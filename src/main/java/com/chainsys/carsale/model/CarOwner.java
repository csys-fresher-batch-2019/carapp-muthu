package com.chainsys.carsale.model;

public class CarOwner {
	private int ownerId;
	private String ownerName;
	private long contactNo;
	private String address1;
	private int pincode;
	private String address2;
	private String city;
	private String state;
	private String password;
	private CarDetail carDetail;

	public int getPincode() {
		return pincode;
	}

	@Override
	public String toString() {
		return "CarOwner [ownerId=" + ownerId + ", ownerName=" + ownerName + ", contactNo=" + contactNo + ", address1="
				+ address1 + ", pincode=" + pincode + ", address2=" + address2 + ", city=" + city + ", state=" + state
				+ ", password=" + password + ", carDetail=" + carDetail + "]";
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public CarDetail getCarDetail() {
		return carDetail;
	}

	public void setCarDetail(CarDetail carDetail) {
		this.carDetail = carDetail;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setownerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getownerId() {
		return ownerId;
	}

	public void setownerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getownerName() {
		return ownerName;
	}

}
