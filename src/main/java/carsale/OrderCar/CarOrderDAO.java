package carsale.OrderCar;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public interface CarOrderDAO {
 public void orderCar(CarOrder carOrder) throws Exception;
public ArrayList<CarOrder> getCarDeleveryDate(int orderId)throws Exception;
public ArrayList<CarOrder>  getDeliveryCarDet(int orderId)throws Exception;
//public void updateCarStatus(int carId)throws Exception;
}