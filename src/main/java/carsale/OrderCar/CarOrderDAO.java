package carsale.OrderCar;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public interface CarOrderDAO {
 public void orderCar(CarOrder carorder) throws Exception;
public ArrayList<CarOrder> getCarDeleveryDate(int orderId)throws Exception;
public ArrayList<CarOrder>  getDeliveryCarDet(String deliver)throws Exception;
//public void updateCarStatus(int carId)throws Exception;
}