package carsale.carDetail;

import java.util.ArrayList;

public interface carDetailDAO {
	void addCarDetail(CarDetail carDetail) throws Exception;
   public  ArrayList<CarDetail> getCarDetail(String carname) throws Exception;
   public ArrayList<CarDetail>  getCarDetail(String carbrand,String regState)throws Exception; 
   public ArrayList<CarDetail>  getDetailWithOwner(String carbrand)throws Exception;
   public ArrayList<CarDetail>  getUpdatedCar(String Status)throws Exception;
   public ArrayList<CarDetail>  getCarDetail(String carname,String car_brand,String fuleType)throws Exception;
    
}
