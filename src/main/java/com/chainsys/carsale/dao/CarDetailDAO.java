package com.chainsys.carsale.dao;

import java.util.ArrayList;

import com.chainsys.carsale.model.CarDetail;

public interface CarDetailDAO {
	void addCarDetail(CarDetail carDetail) throws Exception;

	public ArrayList<CarDetail> getCarDetail(String carName) throws Exception;

	public ArrayList<CarDetail> getCarDetail(String carBrand, String regState) throws Exception;

	public ArrayList<CarDetail> getDetailWithOwner(String carBrand) throws Exception;

	public ArrayList<CarDetail> getUpdatedCar(String Status) throws Exception;

	public ArrayList<CarDetail> getCarDetail(String carName, String carBrand, String fuleType) throws Exception;

	public int verifyUser(int sellerIdd, String password) throws Exception;

	public int getSellerId(Long mobileNo, String password) throws Exception;

	public ArrayList<CarDetail> getCarDetailAbovePrice(float max, String carBrand) throws Exception;

	public ArrayList<CarDetail> getCarDetail(Float max, String carBrand) throws Exception;

	public ArrayList<CarDetail> getCarDetailBelowPrice(Float max, String carBrand) throws Exception;

	public ArrayList<CarDetail> getCarDetailAboveDrivenKm(float from, float to) throws Exception;

	public ArrayList<CarDetail> getCarDetailUseFuelType(String fuelType) throws Exception;
}
