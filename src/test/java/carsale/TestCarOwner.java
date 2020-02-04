package carsale;

import java.util.ArrayList;
import java.util.Scanner;

import carsale.carDetail.CarDetail;
import carsale.ownerDetail.CarOwner;
import carsale.ownerDetail.CarOwnerDAO;
import carsale.ownerDetail.CarOwnerImp;

public class TestCarOwner {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CarOwnerImp obj=new CarOwnerImp();
		CarOwner c=new CarOwner();
		/*int sellno=18;
		String sellername="sundar";
		long contactno=8970893828l;
	     CarOwner c=new CarOwner();
	     c.setownerId(sellno);
	     c.setownerName(sellername);
	     c.setcontactNo(contactno);
	     obj.AddCarOwner(c);*/
		Scanner sc=new Scanner(System.in);
		char m;
		do
		{
		System.out.println("1.View Your car");
		System.out.println("2.Update your Car Price");
		System.out.println("3.Remove your car");
		
	     int ch=sc.nextInt();
		switch(ch)
		{	
		case 1:
		{
		System.out.println("Enter Your Phone Number");
		long mobileno=sc.nextLong();
        ArrayList<CarOwner> al=obj.ViewYourCAr(mobileno);
        for(CarOwner co:al)
        {
        	System.out.println(co.getownerName()+"   "+co.getCardetail().getCarBrand()+"    "+co.getCardetail().getCarName()+"   "+co.getownerId()+"   "+co.getCardetail().getRegYear()+"   "+co.getCardetail().getVehicleIdNo()+"   "+co.getCardetail().getRegistrationNo()+"  "+co.getCardetail().getDrivenKm()+" "+co.getCardetail().getPrice());
        }
        if(al.isEmpty()) {
			System.out.println(" Invalid phone number");
	         
        }
        break;
		}
		case 2:
		{
		char ms;
		do
		{
		System.out.println("Enter  Your Choice to view car ");
		System.out.println("1 for seller_id ");
		System.out.println("2 Mobile Number");
		int choice=sc.nextInt();
		switch(choice)
		{
			case 1: {
				System.out.println("Enter your id");
				c.setownerId(sc.nextInt());
				System.out.println("Enter Your car id");
				CarDetail cd = new CarDetail();
				cd.setCarId(sc.nextInt());
				System.out.println("Enter your car price");
				Float f=sc.nextFloat();
				cd.setPrice(f);
				c.setCardetail(cd);
				obj.updateCarPrice(c);
				break;
			}
			case 2:
			{
				System.out.println("Enter Your phone Number");
				c.setContactNo(sc.nextLong());
				System.out.println("Enter Your car id");
				CarDetail cd=new CarDetail();
				cd.setCarId(sc.nextInt());
				System.out.println("Enter your car price");
			    cd.setPrice(sc.nextFloat());
			    c.setCardetail(cd);
				obj.updateCarPrice(c);
				break;
		
		}
		}
		System.out.println("do You want to continue y/n");
		ms=sc.next().charAt(0);
		}while(ms=='y' || ms=='Y');
		break;
		}
		case 3:
		{
			CarOwnerImp coi=new CarOwnerImp();
			System.out.println("Enter the Car Owner Id");
			int carOwnerId=sc.nextInt();
			System.out.println("Enter your car id");
			int carId=sc.nextInt();
			coi.deletecarDetail(carOwnerId,carId);
       break;
		}
		}
    System.out.println("Do you want to continue");
	m=sc.next().charAt(0);	
	}while(m=='y' || m=='Y');
	
}
}	

