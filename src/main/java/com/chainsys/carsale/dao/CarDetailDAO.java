package com.chainsys.carsale.dao;

import java.util.ArrayList;
import java.util.List;

import com.chainsys.carsale.model.CarDetail;

public interface CarDetailDAO {
	void addCarDetail(CarDetail carDetail) throws Exception;

	public List<CarDetail> getCarDetail(String carName) throws Exception;

	public List<CarDetail> getCarDetail(String carBrand, String regState) throws Exception;

	public List<CarDetail> getDetailWithOwner(String carBrand) throws Exception;

	public List<CarDetail> getUpdatedCar(String Status) throws Exception;

	public List<CarDetail> getCarDetail(String carName, String carBrand, String fuleType) throws Exception;

	public int verifyUser(int sellerIdd, String password) throws Exception;

	public int getSellerId(Long mobileNo, String password) throws Exception;

	public List<CarDetail> getCarDetailAbovePrice(float max, String carBrand) throws Exception;

	public List<CarDetail> getCarDetail(Float max, String carBrand) throws Exception;

	public List<CarDetail> getCarDetailBelowPrice(Float max, String carBrand) throws Exception;

	public List<CarDetail> getCarDetailAboveDrivenKm(float from, float to) throws Exception;

	public List<CarDetail> getCarDetailUseFuelType(String fuelType) throws Exception;
}
