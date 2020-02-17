package com.chainsys.carsale.model;

import java.time.LocalDate;

 public class CarDetail {
	 private int carOwnerId;
	private int carId;
    private String carBrand;
	private String carName;
    private String trType;
    private String fuelType;
	private String regState;
	private int regYear;
	private String registrationNo;
	private int drivenKm;
	private LocalDate updateDate;
	private int price;
	private String status;
	private String carAvailableCity;
	private String  imageSrc;
    public String getImageSrc() {
		return imageSrc;
	}
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	public int getIsOwner() {
		return isOwner;
	}
	public void setIsOwner(int isOwner) {
		this.isOwner = isOwner;
	}



	private String vehicleIdNo;
    private int isOwner;
    private Long contactNo;
    private String password;
    
    public Long getContactNo() {
		return contactNo;
	}
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCarOwnerId() {
		return carOwnerId;
	}
	public void setCarOwnerId(int carOwnerId) {
		this.carOwnerId = carOwnerId;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getTrType() {
		return trType;
	}
	public void setTrType(String trType) {
		this.trType = trType;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getRegState() {
		return regState;
	}
	public void setRegState(String regState) {
		this.regState = regState;
	}
	public int getRegYear() {
		return regYear;
	}
	public void setRegYear(int regYear) {
		this.regYear = regYear;
	}
	public String getVehicleIdNo() {
		return vehicleIdNo;
	}
	public void setVehicleIdNo(String vehicleIdNo) {
		this.vehicleIdNo = vehicleIdNo;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	public int getDrivenKm() {
		return drivenKm;
	}
	public void setDrivenKm(int drivenKm) {
		this.drivenKm = drivenKm;
	}
	public LocalDate getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
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
	public String getCarAvailableCity() {
		return carAvailableCity;
	}
	public void setCarAvailableCity(String carAvailableCity) {
		this.carAvailableCity = carAvailableCity;
	}
	public CarOwner getCarOwner() {
		return carOwner;
	}
	public void setCarOwner(CarOwner carOwner) {
		this.carOwner = carOwner;
	}
	
	
	
	private CarOwner carOwner;
	@Override
	public String toString() {
		return "CarDetail [carOwnerId=" + carOwnerId + ", carId=" + carId + ", carBrand=" + carBrand + ", carName="
				+ carName + ", trType=" + trType + ", fuelType=" + fuelType + ", regState=" + regState + ", regYear="
				+ regYear + ", registrationNo=" + registrationNo + ", drivenKm=" + drivenKm + ", updateDate="
				+ updateDate + ", price=" + price + ", status=" + status + ", carAvailableCity=" + carAvailableCity
				+ ", vehicleIdNo=" + vehicleIdNo + ", isOwner=" + isOwner + ", contactNo=" + contactNo + ", password="
				+ password + ", carOwner=" + carOwner + "]";
	}
	
}
