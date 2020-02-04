package carsale.ownerDetail;

import java.util.ArrayList;

public interface CarOwnerDAO {
    	
	void AddCarOwner(CarOwner carOwner)throws Exception;
	 void deletecarDetail(int carOwnerId,int carId)throws Exception;
     public ArrayList<CarOwner> ViewYourCAr(long mobileno)throws Exception;
     void updateCarPrice(CarOwner carOwner)throws Exception; 
     public boolean isCarOwnerAlreadyRegistered(Long mobileNo) throws Exception;
}
