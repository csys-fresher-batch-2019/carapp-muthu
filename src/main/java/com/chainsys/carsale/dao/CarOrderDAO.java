package com.chainsys.carsale.dao;

import java.util.ArrayList;
import java.util.List;

import com.chainsys.carsale.model.CarOrder;

public interface CarOrderDAO {
 public void orderCar(CarOrder carOrder) throws Exception;
public List<CarOrder> getCarDeleveryDate(int orderId)throws Exception;
public List<CarOrder>  getDeliveryCarDet(int orderId)throws Exception;
//public void updateCarStatus(int carId)throws Exception;
}