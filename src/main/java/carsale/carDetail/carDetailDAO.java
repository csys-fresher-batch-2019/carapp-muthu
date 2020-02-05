package carsale.carDetail;

import java.util.ArrayList;

public interface carDetailDAO {
	void addCarDetail(CarDetail carDetail) throws Exception;
   public  ArrayList<CarDetail> getCarDetail(String carName) throws Exception;
   public ArrayList<CarDetail>  getCarDetail(String carBrand,String regState)throws Exception; 
   public ArrayList<CarDetail>  getDetailWithOwner(String carBrand)throws Exception;
   public ArrayList<CarDetail>  getUpdatedCar(String Status)throws Exception;
   public ArrayList<CarDetail>  getCarDetail(String carName,String carBrand,String fuleType)throws Exception;
   public int verifyUser(int sellerIdd, String password) throws Exception;
   public int getSellerId(Long mobileNo, String password) throws Exception;
   
}
