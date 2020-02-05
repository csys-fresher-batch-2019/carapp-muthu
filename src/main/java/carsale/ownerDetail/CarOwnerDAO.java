package carsale.ownerDetail;

import java.util.ArrayList;

import carsale.OrderCar.CarOrder;

public interface CarOwnerDAO {
    	
	void AddCarOwner(CarOwner carOwner)throws Exception;
	 void deleteCarDetail(int carOwnerId,int carId)throws Exception;
     public ArrayList<CarOwner> ViewYourCar(long mobileNo)throws Exception;
     void updateCarPrice(CarOwner carOwner)throws Exception; 
     public boolean isCarOwnerAlreadyRegistered(Long mobileNo) throws Exception;
     public ArrayList<CarOrder> ViewYourPlacedCar(Long mobileNo)throws Exception;
}
