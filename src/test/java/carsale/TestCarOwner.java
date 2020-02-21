package carsale;

import java.util.List;
import java.util.Scanner;

import com.chainsys.carsale.dao.impl.CarOwnerImp;
import com.chainsys.carsale.logger.Logger;
import com.chainsys.carsale.model.CarDetail;
import com.chainsys.carsale.model.CarOrder;
import com.chainsys.carsale.model.CarOwner;

public class TestCarOwner {
	private static final Logger log=Logger.getInstance();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CarOwnerImp obj = new CarOwnerImp();
		CarOwner c = new CarOwner();
		/*
		 * int sellno=18; String sellername="sundar"; long contactno=8970893828l;
		 * CarOwner c=new CarOwner(); c.setownerId(sellno); c.setownerName(sellername);
		 * c.setcontactNo(contactno); obj.AddCarOwner(c);
		 */
		Scanner sc = new Scanner(System.in);
		char m;
		do {
			log.getInput("1.View Your car");
			log.getInput("2.Update your Car Price");
			log.getInput("3.Remove your car");
			log.getInput("4.view your status");

			int ch = sc.nextInt();
			switch (ch) {
			case 1: {
				log.getInput("Enter Your Phone Number or sellerID");
				long mobileno = sc.nextLong();
				List<CarOwner> al = obj.viewYourCar(mobileno);
				for (CarOwner co : al) {
					log.info(co.getownerName() + "   " + co.getCarDetail().getCarBrand() + "    "
							+ co.getCarDetail().getCarName() + "   " + co.getOwnerId() + "   "
							+ co.getCarDetail().getRegYear() + "   " + co.getCarDetail().getVehicleIdNo() + "   "
							+ co.getCarDetail().getRegistrationNo() + "  " + co.getCarDetail().getDrivenKm() + " "
							+ co.getCarDetail().getPrice() + "" + co.getCarDetail().getCarAvailableCity());
				}
				if (al.isEmpty()) {
					log.error(" Invalid phone number");

				}
				break;
			}
			case 2: {
				char ms;
				do {
					log.getInput("Enter  Your Choice to view car ");
					log.getInput("1 for seller_id ");
					log.getInput("2 Mobile Number");
					int choice = sc.nextInt();
					switch (choice) {
					case 1: {
						log.getInput("Enter your id");
						c.setOwnerId(sc.nextInt());
						log.getInput("Enter Your car id");
						CarDetail cd = new CarDetail();
						cd.setCarId(sc.nextInt());
						log.getInput("Enter your car price");
						int f = sc.nextInt();
						cd.setPrice(f);
						c.setCarDetail(cd);
						obj.updateCarPrice(c);
						break;
					}
					case 2: {
						log.getInput("Enter Your phone Number");
						c.setContactNo(sc.nextLong());
						log.getInput("Enter Your car id");
						CarDetail cd = new CarDetail();
						cd.setCarId(sc.nextInt());
						log.getInput("Enter your car price");
						cd.setPrice(sc.nextInt());
						c.setCarDetail(cd);
						obj.updateCarPrice(c);
						break;

					}
					}
					log.getInput("do You want to continue y/n");
					ms = sc.next().charAt(0);
				} while (ms == 'y' || ms == 'Y');
				break;
			}
			case 3: {
				CarOwnerImp coi = new CarOwnerImp();
				log.getInput("Enter the Car Owner Id");
				int carOwnerId = sc.nextInt();
				log.getInput("Enter your car id");
				int carId = sc.nextInt();
				coi.deleteCarDetail(carOwnerId, carId);
				break;
			}
			case 4: {
				log.getInput("enter your Mobileno");
				Long mobileNo = sc.nextLong();
				List<CarOrder> al = obj.viewYourPlacedCar(mobileNo);
				for (CarOrder ca : al) {
					log.info(+ca.getBuyerContactNo() + "   " + ca.getBuyerName() + "  " + ca.getAddress1()
							+ "  " + ca.getAddress2() + "  " + ca.getPincode() + "  " + ca.getBuyerState() + "   "
							+ ca.getCarId() + "  " + ca.getOrderId() + "  " + ca.getStatus() + "  "
							+ ca.getDeliveredDate() + "    " + ca.getOrderedDate());
				}
				break;
			}
			case 5:
			{
				log.getInput("Enter mobile no/userId");
				Long mobileNo = sc.nextLong();
				boolean exist=obj.isCarOwnerAlreadyRegistered(mobileNo);
				if(exist)
				{
					log.info(exist);
				}
				else
				{
					log.info("1");
				}
			}
			}
			log.getInput("Do you want to continue");
			m = sc.next().charAt(0);
		} while (m == 'y' || m == 'Y');
sc.close();
	}
}
