package carsale;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.chainsys.carsale.dao.impl.CarOrderImp;
import com.chainsys.carsale.logger.Logger;
import com.chainsys.carsale.model.CarOrder;

public class TestCarOrder {
	private static final Logger log=Logger.getInstance();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		char m;
		do {
			log.getInput("1.order the car!!");
			log.getInput("2.get your car delivery Date!!!!");
			log.getInput("3.view Your car Delivery Information");
			log.getInput("4.view your ordered car");
             log.getInput("5.View ordered  your Car ");
			int ch = sc.nextInt();
			switch (ch) {
			case 1: {
				//LocalDate ldd = LocalDate.now();
				log.getInput("Enter the buyer name");
				String buyerName = sc.next();
				log.getInput("Enter the  buyer contact no");
				long contactno = sc.nextLong();
				log.getInput("Enter the car id");
				int carId = sc.nextInt();
				log.getInput("Enter the seller id");
				int sellerId = sc.nextInt();
				log.getInput("Aru you apply Test Drive?");
				String testDrive = sc.next();
				log.getInput("Enter Delivery Address");
				log.getInput("Enter the Streetname");
				String streetName = sc.next();
				log.getInput("Enter the doorno");
				String doorNo = sc.next();
				log.getInput("Enter the city");
				String city = sc.next();
				log.getInput("Enter the state");
				String state = sc.next();
				log.getInput("Enter the pincode");
				int pincode = sc.nextInt();
				log.getInput("Enter the userId");
				int userId=sc.nextInt();
				CarOrderImp obj = new CarOrderImp();
				CarOrder c = new CarOrder();
				c.setBuyerName(buyerName);
				c.setBuyerContactNo(contactno);
				c.setCarId(carId);
				c.setSellerId(sellerId);
				c.setTestDrive(testDrive);
				c.setAddress1(streetName);
				c.setAddress2(doorNo);
				c.setCity(city);
				c.setBuyerState(state);
				c.setPincode(pincode);
				c.setUserId(userId);
				obj.orderCar(c);
				break;
			}
			case 2: {
				CarOrderImp obj = new CarOrderImp();
				log.getInput("Enter the order_id");
				int id = sc.nextInt();
				List<CarOrder> cars = obj.getCarDeleveryDate(id);
				String fileContent = "";
				for (CarOrder c : cars) {
					String data = c.getBuyerName() + "," + c.getOrderId() + "," + c.getCarId() + ","
							+ c.getDeliveredDate();
					fileContent = fileContent + data + "\n";
				}
				log.getInput(fileContent);
				Path path = Paths.get("d:\\mycars.txt");
				Files.write(path, fileContent.getBytes());
				break;
			}
			case 3: {
				
				LocalDate ldd = LocalDate.now();

				//Date daa = Date.valueOf(ldd);
				CarOrderImp co = new CarOrderImp();
				log.getInput("Enter the order_id");
				int orderId = sc.nextInt();
				List<CarOrder> al = co.getDeliveryCarDet(orderId);
				for (CarOrder c : al) {
					LocalDate deliveredDate = c.getDeliveredDate().toLocalDate();
					long days = Duration.between(ldd.atTime(0, 0), deliveredDate.atTime(0, 0)).toDays();

					log.getInput(c.getCarName() + " " + deliveredDate + " " + c.getBuyerName());
					if (deliveredDate.isBefore(ldd)) {
						/*
						 * LocalDate ld1=c.getDeliveredDate().toLocalDate(); LocalDate
						 * d=ldd.minus(1,ChronoUnit.DAYS); Date dd=new
						 * Date((c.deliveredDate).getTime()-daa.getTime()); java.util.Date ddate=new
						 * Date(deliveredDate.getTime()); java.util.Date currentDate=new
						 * java.util.Date();
						 */
						log.info("Delivered");
					
					} else if (deliveredDate.isAfter(ldd)) {

						log.info(days + "  more days!!!");

					} else {
						log.getInput("Delivered today!!!!");
					}
				}

				break;
				
			}
			case 4:
			{
				CarOrderImp co=new CarOrderImp();
				log.getInput("Enter userId");
				int userId=sc.nextInt();
				List<CarOrder> li=co.getOrderedCar(userId);
				for(CarOrder c:li)
				{
				 log.info(c.getBuyerName() + "," + c.getOrderId() + "," + c.getCarId() + ","+ c.getDeliveredDate());
				}
				break;
				
			}
			case 5:
			{
				CarOrderImp co=new CarOrderImp();
				log.getInput("Enter userId");
				int userId=sc.nextInt();
				List<CarOrder> li=co.getOrderedUserCar(userId);
				for(CarOrder c:li)
				{
				 log.info(c.getBuyerName() + "," + c.getOrderId() + "," + c.getCarId() + ","+ c.getDeliveredDate());
				}
				break;
			}
			default:
				log.error("enter the valid option");
				break;
			}
			log.getInput("Do you want to continue Y/n");
			m = sc.next().charAt(0);
		} while (m == 'y' || m == 'Y');
		sc.close();
	}

	/*
	 * private static void callGetDelivery() throws Exception {
	 * 
	 * } /*public static void callupdate()throws Exception { CarOrderImp obj=new
	 * CarOrderImp(); Scanner sc=new Scanner(System.in);
	 * log.getInput("enter the car number"); int carid=sc.nextInt(); CarDetail
	 * cd=new CarDetail(); cd.carId=carid; obj.updateCarStatus(cd.carId);
	 * 
	 * 
	 * }
	 */
}
