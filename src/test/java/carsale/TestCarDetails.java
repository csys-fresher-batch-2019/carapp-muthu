package carsale;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.chainsys.carsale.dao.impl.CarDetailImp;
import com.chainsys.carsale.dao.impl.CarOwnerImp;
import com.chainsys.carsale.logger.Logger;
import com.chainsys.carsale.model.CarDetail;
import com.chainsys.carsale.model.CarOwner;

public class TestCarDetails {
	private static final Logger log = Logger.getInstance();

	public static void main(String[] args) throws Exception
	{
		CarDetailImp co=new CarDetailImp();
		CarDetail cd=new CarDetail();
		Scanner sc=new Scanner(System.in);
	    
	    char m;
		do
		{
		log.getInput("1.Add(sell) Your car");
		log.getInput("2.view all (not)available car");
		log.getInput("3.get specific car information");
		log.getInput("4.List out all available cars in specifc state");
		log.getInput("5.list out specific car detail with seller");
		log.getInput("6.search by car price(above) and brand");
		log.getInput("7.serach by car price(below) and Car Brand");
		log.getInput("8.search by car Driven Kilometer");
		log.getInput("9. search By Fuel Type");
		log.getInput("10.search by car id;");
		log.getInput("11.view All cars");
		log.getInput("12.RegNo");
		int cho=sc.nextInt();
		switch(cho)
		{
		case 1:
		{
			
			char mesg;
			do {
				
				log.getInput("1.new User");
				log.getInput("2.Existing User");
				log.getInput("3.Exit");
			    int ch=sc.nextInt();
			    switch(ch)
			    {
			    case 1:
			    {
			    					
			        CarOwner coo=new CarOwner();
			        log.getInput("Enter the contact no");
					long mobileno=sc.nextLong();
					CarOwnerImp coi=new CarOwnerImp();
					boolean exists = coi.isCarOwnerAlreadyRegistered(mobileno);
					if(exists==false)
					{
						log.getInput("Enter the seller name");
					coo.setownerName(sc.next());
					log.getInput("Enter the password");
					coo.setPassword(sc.next());
					log.getInput("Enter the Street name");
					coo.setAddress1(sc.next());
					log.getInput("Enter the street No and door no");
					coo.setAddress2(sc.next());
					log.getInput("Enter the city");
					coo.setCity(sc.next());
					log.getInput("Enter the state");
					coo.setState(sc.next());
					log.getInput("Enter pincode");
					coo.setPincode(sc.nextInt());
					coo.setContactNo(mobileno);
					CarOwnerImp coii=new CarOwnerImp();
					coii.addCarOwner(coo);
					}
					else
					{
						log.getInput("user already Exists");
					}
			    	break;
			    }
		case 2:
		{   
			char msg;
			do
			{
	        log.getInput("choose your Login option");
	        log.getInput("press 1 for Seller id or 2 for mobile no");
	        int choice=sc.nextInt();
	        switch(choice)
	        {
	        case 1:
	        {
	        	
	     	           	log.getInput("Enter the  seller ID");
	     	int sellerId=sc.nextInt();
	        	log.getInput("Enter your password");
			String pass=sc.next();
			
			CarDetailImp ci=new CarDetailImp();
			int sellerIdd=ci.getSellerId((long) sellerId,pass);
			if(sellerIdd!=0)
			{
			log.getInput("Enter car name");
			String carName=sc.next();
			log.getInput("Enter car Brand");
			String carBrand=sc.next();
			log.getInput("Enter DrivenKm");
			int drivenKm=sc.nextInt();
			log.getInput("Enter transmission type");
			String trType=sc.next();
			log.getInput("car Price");
			int price=sc.nextInt();
			log.getInput("Enter the state");
			String regState=sc.next();
			log.getInput("Enter the fuel Type");
			String fuelType=sc.next();
			//log.getInput("Enter the status ");
			//String status=s.next();
			log.getInput("Enter the register year");
			int regYear=sc.nextInt();
			log.getInput("enter the car available city");
			String carAvailabeCity=sc.next();
			log.getInput("Enter the Registration No");
            String registrationNo=sc.next();
            log.getInput("Enter the  vehicle identifcation no");
            String vehicleNo=sc.next();
            log.getInput(" are you  owner(press 1) or intermediatary(press 0)");
            int isOwner=sc.nextInt();
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
		   cardetail.setIsOwner(isOwner);
		  	  		
			   ci.addCarDetail(cardetail);
			}
			else
			{
				log.error("invalid userId and password");
			}
			
		   
			break;
	        }
			
	        case 2:
	        {
	        	CarOwner car=new CarOwner();
	           	log.getInput("Enter the  Mobile Number");
	     	  Long mobileNo=sc.nextLong();
	           	car.setContactNo(mobileNo);
	        	log.getInput("Enter your password");
			  String password=sc.next();
	        	car.setPassword(password);
	        	
			CarDetailImp ci=new CarDetailImp();
			
			
			int sellerId=ci.getSellerId(mobileNo,password);
			//int sellerId=ci.getSellerId2();
			if(sellerId!=0)
			{
			log.getInput("Enter car name");
			String carName=sc.next();
			log.getInput("Enter car Brand");
			String carBrand=sc.next();
			log.getInput("Enter DrivenKm");
			int drivenKm=sc.nextInt();
			log.getInput("Enter transmission type");
			String trType=sc.next();
			log.getInput("car Price");
			int price=sc.nextInt();
			log.getInput("Enter the state");
			String regState=sc.next();
			log.getInput("Enter the fuel Type");
			String fuelType=sc.next();
			//log.getInput("Enter the status ");
			//String status=s.next();
			log.getInput("Enter the register year");
			int regYear=sc.nextInt();
			log.getInput("enter the car available city");
			String carAvailabeCity=sc.next();
			log.getInput("Enter the Registration No");
            String registrationNo=sc.next();
            log.getInput("Enter the  vehicle identifcation no");
            String vehicleNo=sc.next();
            log.getInput( "are you  owner(press 1) or intermediatary(press 0)");
            int isOwner=sc.nextInt();
			
			
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
			
			cardetail.setRegState(regState);
			cardetail.setCarAvailableCity(carAvailabeCity);
		    cardetail.setRegistrationNo(registrationNo);
		   cardetail.setVehicleIdNo(vehicleNo); 
			cardetail.setCarOwner(car);
			cardetail.setIsOwner(isOwner);
		   ci.addCarDetail(cardetail);
			}
			else
			{
				log.error("invalid mobileno or password");
			}
		   break;
	        }
	        }
	        log.getInput("Do you want to continue");
	        msg=sc.next().charAt(0);
	       	} while(msg=='y'||msg=='y');    
	        break;
		}
		case 3:
		{
			System.exit(0);
		}
		default:
			log.error("Enter the valid option");
		}
			    log.getInput("Do You want to continue y/n");
			    mesg=sc.next().charAt(0);	   
			    }while(mesg=='y' || mesg=='Y');
				
	
			

		break;
		}
		case 2:
		{
			CarDetailImp cdi=new CarDetailImp();
			
			log.getInput("Enter the car status 1. Available 2. Not Available");
			int choice=sc.nextInt();
			String status = choice ==1 ? "available" : "not available" ; 
			List<CarDetail> al=cdi.getUpdatedCar(status);
			for(CarDetail ss:al)
			{
				//log.getInput(ss.toString());
				log.info(ss.getCarOwner().getownerName()+"  "+ss.getCarOwner().getContactNo()+"  "+ss.getCarBrand()+"   "+ss.getCarName()+"  "+ss.getCarId()+"   "+ss.getDrivenKm()+"   "+ss.getFuelType()+"   "+ss.getRegState()+""+ss.getRegYear()+"  "+ss.getTrType()+"  "+ss.getStatus()+"  "+ss.getRegistrationNo());
			}
		break;
		}
		case 3:
		{
			log.getInput(" enter preffered Car Brand");
			cd.setCarBrand(sc.next());
			List<CarDetail>al=co.getCarDetail(cd.getCarBrand());
			for(CarDetail cdl:al)
			{
			log.info(cdl.getCarId()+" "+cdl.getCarName()+"   "+cdl.getCarBrand()+"   "+cdl.getTrType()+"    "+cdl.getFuelType()+"   "+cdl.getRegState()+"   "+cdl.getRegYear()+"   "+cdl.getDrivenKm()+"   "+cdl.getPrice()+"    "+cdl.getStatus()+"   "+cdl.getRegistrationNo()+"    "+cdl.getCarOwner().getownerName()+"   "+cdl.getImageSrc());
			}	
		break;
		}
		case 4:
		{
			log.getInput("Enter the car brand");
		    cd.setCarBrand(sc.next());
		    log.getInput("Enter the state");
		    cd.setRegState(sc.next());
		    List<CarDetail> al=co.getCarDetail(cd.getCarBrand(),cd.getRegState());
			for(CarDetail cdl:al)
			{
				//log.getInput(cdl.toString());
				log.info(cdl.getCarName()+"    "+cdl.getCarBrand()+"    "+cdl.getTrType()+"  "+cdl.getFuelType()+"   "+cdl.getRegState()+"    "+cdl.getRegYear()+"  "+cdl.getDrivenKm()+"   "+cdl.getPrice()+"  "+cdl.getStatus()+"   "+cdl.getRegistrationNo());
				
			}
			break;
		}
		case 5:
		{
			log.getInput("Enter the car brand");
			cd.setCarBrand(sc.next());
		List<CarDetail> carList = co.getDetailWithOwner(cd.getCarBrand());
		for (CarDetail carDetail : carList) {
			
	       log.info(carDetail);
		}
		break;
		}
		case 6:
		{
			log.getInput("Enter the minium car price");
			float minPrice=sc.nextFloat();
			//log.getInput("Enter the car Brand");
			//String carBrand=sc.next();
			List<CarDetail> ar=co.getCarDetailAbovePrice(minPrice);
			for(CarDetail cdr:ar)
			{
				log.info(cdr.getCarOwnerId()+""+cdr.getImageSrc()+""+cdr.getCarName()+"    "+cdr.getCarBrand()+"    "+cdr.getTrType()+"  "+cdr.getFuelType()+" "+cdr.getCarId()+"  "+cdr.getCarAvailableCity()+"   "+cdr.getRegYear()+"  "+cdr.getDrivenKm()+"   "+cdr.getPrice()+"  "+cdr.getStatus()+"   "+cdr.getRegistrationNo());
				
				}
			if(ar.isEmpty())
			{
				log.getInput("oops!!! car not found");
			}
			break;
			
		}
		case 7:
		{
			log.getInput("Enter the maximum car price");
			float maxPrice=sc.nextFloat();
			//log.getInput("Enter the car Brand");
			//String carBrand=sc.next();
			List<CarDetail> ar=co.getCarDetailBelowPrice(maxPrice);
			for(CarDetail cdr:ar)
			{
				log.info(cdr.getCarOwnerId()+""+cdr.getImageSrc()+" "+cdr.getCarName()+"    "+cdr.getCarBrand()+"    "+cdr.getTrType()+"  "+cdr.getFuelType()+" "+cdr.getCarId()+"  "+cdr.getCarAvailableCity()+"   "+cdr.getRegYear()+"  "+cdr.getDrivenKm()+"   "+cdr.getPrice()+"    "+cdr.getRegistrationNo());
				
				}
			break;
			
		}
		case 8:
		{
			log.getInput(" Kilometer start FROM:");
			float start=sc.nextFloat();
			log.getInput("Kilometer End TO:");
			float end=sc.nextFloat();
			List<CarDetail> ar=co.getCarDetailAboveDrivenKm(start,end);
			for(CarDetail cdr:ar)
			{
				log.info(cdr.getCarName()+"    "+cdr.getCarBrand()+"    "+cdr.getTrType()+"  "+cdr.getFuelType()+" "+cdr.getCarId()+"  "+cdr.getCarAvailableCity()+"   "+cdr.getRegYear()+"  "+cdr.getDrivenKm()+"   "+cdr.getPrice()+"    "+cdr.getRegistrationNo());
				
				}
			if(ar.isEmpty())
			{
				log.getInput(" oops!!! car not found");
			}
			break;
			
		}
		case 9:
		{
			log.getInput("Enter  FuelType:");
			String fuelType=sc.next();
			List<CarDetail> ar=co.getCarDetailUseFuelType(fuelType);
			for(CarDetail cdr:ar)
			{
				log.info(cdr.getCarName()+"    "+cdr.getCarBrand()+"    "+cdr.getTrType()+"  "+cdr.getFuelType()+" "+cdr.getCarId()+"  "+cdr.getCarAvailableCity()+"   "+cdr.getRegYear()+"  "+cdr.getDrivenKm()+"   "+cdr.getPrice()+"    "+cdr.getRegistrationNo());
				
			}
			break;
		}
		case 10:
		{
			log.getInput(" enter  car id");
			int carId=sc.nextInt();
			cd.setCarId(carId);
			List<CarDetail>al=co.getCarDetailUseCarId(cd.getCarId());
			for(CarDetail cdl:al)
			{
			log.info(cdl.getCarOwnerId()+"   "+cdl.getCarId()+" "+cdl.getCarName()+"   "+cdl.getCarBrand()+"   "+cdl.getTrType()+"    "+cdl.getFuelType()+"   "+cdl.getRegState()+"   "+cdl.getRegYear()+"   "+cdl.getDrivenKm()+"   "+cdl.getPrice()+"    "+cdl.getStatus()+"   "+cdl.getRegistrationNo()+"    "+cdl.getCarOwner().getownerName()+"   "+cdl.getImageSrc()+"   "+cdl.getCarOwner().getContactNo());
			}
			break;
		}
		case 11:
		{
		List<CarDetail> list=co.viewAllCar();
		for(CarDetail cdl:list)
		{
		log.info(cdl.getCarOwnerId()+"   "+cdl.getCarId()+" "+cdl.getCarName()+"   "+cdl.getCarBrand()+"   "+cdl.getTrType()+"    "+cdl.getFuelType()+"   "+cdl.getRegState()+"   "+cdl.getRegYear()+"   "+cdl.getDrivenKm()+"   "+cdl.getPrice()+"    "+cdl.getStatus()+"   "+cdl.getRegistrationNo());
		}
		}
		break;
		case 12:
		{
		  String regNo="TN69BA897";
			CarDetailImp cdi=new CarDetailImp();
			boolean ex=cdi.isCarAlreadyRegistered(regNo);
		System.out.println(ex);
		}
		break;
		default:
			log.error("Enter valid option");
			break;
		}
	log.getInput("Do You want to continue y/n");
     m=sc.next().charAt(0);
        }while(m=='y'||m=='Y');
		sc.close();
	}
	
	
			
}
