package carsale.OrderCar;

import java.sql.Date;
import java.time.LocalDate;

public class CarOrder {
private int orderId;
private String BuyerName;
private long BuyerContactNo;
private int carid;
private int sellerId;
private String testDrive;
private String status;
private String orderedDate;
private Date deliveredDate;
public int getOrderId() {
	return orderId;
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
public int getCarid() {
	return carid;
}
public void setCarid(int carid) {
	this.carid = carid;
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
public String getOrderedDate() {
	return orderedDate;
}
public void setOrderedDate(String orderedDate) {
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
			+ ", carid=" + carid + ", sellerId=" + sellerId + ", testDrive=" + testDrive + ", status=" + status
			+ ", orderedDate=" + orderedDate + ", deliveredDate=" + deliveredDate + ", carName=" + carName + "]";
}
}
