package com.chainsys.carsale.dao;

import java.util.List;



import com.chainsys.carsale.model.CarOrder;
import com.chainsys.carsale.model.CarOwner;
import com.chainsys.carsale.util.DbException;

public interface CarOwnerDAO {
   	void addCarOwner( CarOwner carOwner) throws DbException;
  void deleteCarDetail(int carOwnerId, int carId) throws DbException;
   public List<CarOwner> viewYourCar(long mobileNo) throws DbException;
  void updateCarPrice(CarOwner carOwner) throws DbException;
   public boolean isCarOwnerAlreadyRegistered(Long mobileNo) throws DbException;
  public List<CarOrder> viewYourPlacedCar(Long mobileNo) throws DbException;
}
