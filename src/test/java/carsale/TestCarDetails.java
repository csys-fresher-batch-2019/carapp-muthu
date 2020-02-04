package carsale;
import carsale.ownerDetail.CarOwner;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Scanner;

import carsale.carDetail.CarDetail;
import carsale.carDetail.CarDetailImp;

import carsale.ownerDetail.CarOwnerImp;
public class TestCarDetails {
	public static void main(String[] args) throws Exception
	{
		CarDetailImp co=new CarDetailImp();
		CarDetail cd=new CarDetail();
		TestCarDetails c=new TestCarDetails();
		Scanner sc=new Scanner(System.in);
	    
	    char m;
		do
		{
		System.out.println("1.Add(sell) Your car");
		System.out.println("2.view all (not)available car");
		System.out.println("3.get specific car information");
		System.out.println("4.List out all available cars in specifc state");
		System.out.println("5.list out specific car detail with seller");
        System.out.println("6.Exit");
		int cho=sc.nextInt();
		switch(cho)
		{
		case 1:
		{
			Scanner s=new Scanner(System.in);
			char mesg;
			do {
				
				System.out.println("1.new User");
				System.out.println("2.Existing User");
				System.out.println("3.Exit");
			    int ch=s.nextInt();
			    switch(ch)
			    {
			    case 1:
			    {
			    					
			        CarOwner coo=new CarOwner();
			       	System.out.println("Enter the contact no");
					long mobileno=s.nextLong();
					CarOwnerImp coi=new CarOwnerImp();
					boolean exists = coi.isCarOwnerAlreadyRegistered(mobileno);
					if(exists==false)
					{
				    System.out.println("Enter the seller name");
					coo.setownerName(s.next());
					System.out.println("Enter the password");
					coo.setPassword(s.next());
					System.out.println("Enter the Street name");
					coo.setAddress1(s.next());
					System.out.println("Enter the street No and door no");
					coo.setAddress2(s.next());
					System.out.println("Enter the city");
					coo.setCity(s.next());
					System.out.println("Enter the state");
					coo.setState(s.next());
					System.out.println("Enter pincode");
					coo.setPincode(s.nextInt());
					coo.setContactNo(mobileno);
					CarOwnerImp coii=new CarOwnerImp();
					coii.AddCarOwner(coo);
					}
					else
					{
						System.out.println("user already Exists");
					}
			    	break;
			    }
		case 2:
		{   
			char msg;
			do
			{
	        System.out.println("choose your Login option");
	        System.out.println("press 1 for Seller id or 2 for mobile no");
	        int choice=s.nextInt();
	        switch(choice)
	        {
	        case 1:
	        {
	     	           	System.out.println("Enter the  seller ID");
	     	int sellerId=s.nextInt();
	        	System.out.println("Enter your password");
			String pass=s.next();
			CarDetailImp ci=new CarDetailImp();
			int sellerIdd=ci.verifyUser(sellerId, pass);
			if(sellerIdd!=0)
			{
			System.out.println("Enter car name");
			String carName=s.next();
			System.out.println("Enter car Brand");
			String carBrand=s.next();
			System.out.println("Enter DrivenKm");
			Float drivenKm=s.nextFloat();
			System.out.println("Enter transmission type");
			String trType=s.next();
			System.out.println("car Price");
			int price=s.nextInt();
			System.out.println("Enter the state");
			String regState=s.next();
			System.out.println("Enter the fuel Type");
			String fuelType=s.next();
			//System.out.println("Enter the status ");
			//String status=s.next();
			System.out.println("Enter the register year");
			int regYear=s.nextInt();
			System.out.println("enter the car available city");
			String carAvailabeCity=s.next();
			System.out.println("Enter the Registration No");
            String registrationNo=s.next();
            System.out.println("Enter the  vehicle identifcation no");
            String vehicleNo=s.next();
			LocalDate updatedDate=LocalDate.now();
			
			CarOwner cown=new CarOwner();
			cown.setPassword(pass);
		    CarDetail cardetail = new CarDetail();
			//cardetail.setCarOwnerId(carOwnerId);
			cardetail.setCarBrand(carBrand);
			cardetail.setCarName(carName);
			cardetail.setPrice(price);
			cardetail.setCarOwnerId(sellerId);
			//cardetail.setStatus(status);
			cardetail.setFuelType(fuelType);
			cardetail.setTrType(trType);
			cardetail.setRegYear(regYear);
			cardetail.setDrivenKm(drivenKm);
			cardetail.setUpdateDate(updatedDate);
			cardetail.setRegState(regState);
			cardetail.setCarAvailableCity(carAvailabeCity);
		    cardetail.setRegistrationNo(registrationNo);
		    cardetail.setPassword(pass);
		   cardetail.setVehicleIdNo(vehicleNo);
		   cardetail.setCarOwner(cown);
			 ci.addCarDetail(cardetail);
			}
			else
			{
				System.out.println("invalid userId and password");
			}
			break;
	        }
	        case 2:
	        {
	        	CarOwner car=new CarOwner();
	           	System.out.println("Enter the  Mobile Number");
	     	  Long mobileNo=s.nextLong();
	           	car.setContactNo(mobileNo);
	        	System.out.println("Enter your password");
			  String password=s.next();
	        	car.setPassword(password);
			CarDetailImp ci=new CarDetailImp();
			int sellerId=ci.getSellerId(mobileNo,password);
			if(sellerId!=0)
			{
			System.out.println("Enter car name");
			String carName=s.next();
			System.out.println("Enter car Brand");
			String carBrand=s.next();
			System.out.println("Enter DrivenKm");
			Float drivenKm=s.nextFloat();
			System.out.println("Enter transmission type");
			String trType=s.next();
			System.out.println("car Price");
			int price=s.nextInt();
			System.out.println("Enter the state");
			String regState=s.next();
			System.out.println("Enter the fuel Type");
			String fuelType=s.next();
			//System.out.println("Enter the status ");
			//String status=s.next();
			System.out.println("Enter the register year");
			int regYear=s.nextInt();
			System.out.println("enter the car available city");
			String carAvailabeCity=s.next();
			System.out.println("Enter the Registration No");
            String registrationNo=s.next();
            System.out.println("Etner the  vehicle identifcation no");
            String vehicleNo=s.next();
			LocalDate updatedDate=LocalDate.now();
			
		    CarDetail cardetail = new CarDetail();
			//cardetail.setCarOwnerId(carOwnerId);
			cardetail.setCarBrand(carBrand);
			cardetail.setCarName(carName);
			cardetail.setPrice(price);
			cardetail.setCarOwnerId(sellerId);
			//cardetail.setStatus(status);
			cardetail.setFuelType(fuelType);
			cardetail.setTrType(trType);
			cardetail.setRegYear(regYear);
			cardetail.setDrivenKm(drivenKm);
			cardetail.setUpdateDate(updatedDate);
			cardetail.setRegState(regState);
			cardetail.setCarAvailableCity(carAvailabeCity);
		    cardetail.setRegistrationNo(registrationNo);
		   cardetail.setVehicleIdNo(vehicleNo); 
			cardetail.setCarOwner(car);
		   ci.addCarDetail(cardetail);
			}
			else
			{
				System.out.println("invalid mobileno or password");
			}
		   break;
	        }
	        }
	        System.out.println("Do you want to continue");
	        msg=s.next().charAt(0);
	       	} while(msg=='y'||msg=='y');    
	        break;
		}
		case 3:
		{
			System.exit(0);
		}
		default:
			System.out.println("Enter the valid option");
		}
			    System.out.println("Do You want to continue y/n");
			    mesg=s.next().charAt(0);	   
			    }while(mesg=='y' || mesg=='Y');
				
	
			

		break;
		}
		case 2:
		{
			CarDetailImp cdi=new CarDetailImp();
			Scanner s=new Scanner(System.in);
			System.out.println("Enter the car status 1. Available 2. Not Available");
			int choice=s.nextInt();
			String status = choice ==1 ? "available" : "not available" ; 
			ArrayList<CarDetail> al=cdi.getUpdatedCar(status);
			for(CarDetail ss:al)
			{
				//System.out.println(ss.toString());
				System.out.println(ss.getCarOwner().getownerName()+"  "+ss.getCarOwner().getContactNo()+"  "+ss.getCarBrand()+"   "+ss.getCarName()+"  "+ss.getCarId()+"   "+ss.getDrivenKm()+"   "+ss.getFuelType()+"   "+ss.getRegState()+""+ss.getRegYear()+"  "+ss.getTrType()+"  "+ss.getStatus()+"  "+ss.getRegistrationNo());
			}
		break;
		}
		case 3:
		{
			System.out.println(" enter preffered Car Brand");
			cd.setCarBrand(sc.next());
			ArrayList<CarDetail>al=co.getCarDetail(cd.getCarBrand());
			for(CarDetail cdl:al)
			{
			System.out.println(cdl.getCarName()+"   "+cdl.getCarBrand()+"   "+cdl.getTrType()+"    "+cdl.getFuelType()+"   "+cdl.getRegState()+"   "+cdl.getRegYear()+"   "+cdl.getDrivenKm()+"   "+cdl.getPrice()+"    "+cdl.getStatus()+"   "+cdl.getRegistrationNo()+"    a"+cdl.getCarOwner().getownerName());
			}	
		break;
		}
		case 4:
		{
			System.out.println("Enter the car brand");
		    cd.setCarBrand(sc.next());
		    System.out.println("Enter the state");
		    cd.setRegState(sc.next());
		    ArrayList<CarDetail> al=co.getCarDetail(cd.getCarBrand(),cd.getRegState());
			for(CarDetail cdl:al)
			{
				//System.out.println(cdl.toString());
				System.out.println(cdl.getCarName()+"    "+cdl.getCarBrand()+"    "+cdl.getTrType()+"  "+cdl.getFuelType()+"   "+cdl.getRegState()+"    "+cdl.getRegYear()+"  "+cdl.getDrivenKm()+"   "+cdl.getPrice()+"  "+cdl.getStatus()+"   "+cdl.getRegistrationNo());
				
			}
			break;
		}
		case 5:
		{
			System.out.println("Enter the car brand");
			cd.setCarBrand(sc.next());
		ArrayList<CarDetail> carList = co.getDetailWithOwner(cd.getCarBrand());
		for (CarDetail carDetail : carList) {
			
	       System.out.println(carDetail);
		}
		break;
		}
		case 6:
		{
			
			
		}
		default:
			System.out.println("Enter valid option");
			break;
		}
	System.out.println("Do You want to continue y/n");
     m=sc.next().charAt(0);
        }while(m=='y'||m=='Y');
	}
	
		public void testinsert() throws Exception 
		{
		
		}
					
}
		
		
