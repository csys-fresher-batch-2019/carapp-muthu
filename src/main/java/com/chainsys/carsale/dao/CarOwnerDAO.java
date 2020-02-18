package com.chainsys.carsale.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.chainsys.carsale.model.CarOrder;
import com.chainsys.carsale.model.CarOwner;
import com.chainsys.carsale.util.DbException;

public interface CarOwnerDAO {
   	void addCarOwner(@BindBean CarOwner carOwner) throws DbException;
  void deleteCarDetail(int carOwnerId, int carId) throws DbException;
   public List<CarOwner> viewYourCar(long mobileNo) throws DbException;
  void updateCarPrice(CarOwner carOwner) throws DbException;
   public boolean isCarOwnerAlreadyRegistered(Long mobileNo) throws DbException;
  public List<CarOrder> viewYourPlacedCar(Long mobileNo) throws DbException;
}
