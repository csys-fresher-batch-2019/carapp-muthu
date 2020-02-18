package com.chainsys.carsale.dao;

import java.util.List;

import com.chainsys.carsale.model.CarDetail;
import com.chainsys.carsale.util.DbException;

public interface CarDetailDAO {
	void addCarDetail(CarDetail carDetail) throws DbException;

	public List<CarDetail> getCarDetail(String carName) throws DbException;

	public List<CarDetail> getCarDetail(String carBrand, String regState) throws DbException;

	public List<CarDetail> getDetailWithOwner(String carBrand) throws DbException;

	public List<CarDetail> getUpdatedCar(String status) throws DbException;

	public List<CarDetail> getCarDetail(String carName, String carBrand, String fuleType) throws DbException;

	//public int verifyUser(int sellerIdd, String password) throws DbException;

	//public int getSellerId(Long mobileNo, String password) throws DbException;

	public List<CarDetail> getCarDetailAbovePrice(float max) throws DbException;

	public List<CarDetail> getCarDetail(Float max, String carBrand) throws DbException;

	public List<CarDetail> getCarDetailBelowPrice(Float max) throws DbException;

	public List<CarDetail> getCarDetailAboveDrivenKm(float from, float to) throws DbException;

	public List<CarDetail> getCarDetailUseFuelType(String fuelType) throws DbException;
     public List<CarDetail> getCarDetailUseCarId(int carId) throws DbException;
    public List<CarDetail> viewAllCar() throws DbException;
    
}

