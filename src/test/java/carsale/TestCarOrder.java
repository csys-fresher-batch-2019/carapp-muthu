package carsale;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import carsale.OrderCar.CarOrder;
import carsale.OrderCar.CarOrderImp;
import carsale.carDetail.CarDetail;

public class TestCarOrder {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		char m;
		do {
			System.out.println("1.order the car!!");
			System.out.println("2.get your car delivery Date!!!!");
			System.out.println("3.view Your car Delivery Information");

			int ch = sc.nextInt();
			switch (ch) {
			case 1: {

				System.out.println("Enter the buyer name");
				String buyerName = sc.next();
				System.out.println("Enter the  buyer contact no");
				long contactno = sc.nextLong();
				System.out.println("Enter the car id");
				int carId = sc.nextInt();
				System.out.println("Enter the seller id");
				int sellerId = sc.nextInt();
				System.out.println("Aru you apply Test Drive?");
				String testDrive = sc.next();

				CarOrderImp obj = new CarOrderImp();
				CarOrder c = new CarOrder();
				c.setBuyerName(buyerName);
				c.setBuyerContactNo(contactno);
				c.setCarid(carId);
				c.setSellerId(sellerId);
				c.setTestDrive(testDrive);
				obj.orderCar(c);
				break;
			}
			case 2: {
				CarOrderImp obj = new CarOrderImp();
				System.out.println("Enter the order_id");
				int id = sc.nextInt();
				List<CarOrder> cars = obj.getCarDeleveryDate(id);
				String fileContent = "";
				for (CarOrder c : cars) {
					String data = c.getBuyerName() + "," + c.getOrderId() + "," + c.getCarid() + ","
							+ c.getDeliveredDate();
					fileContent = fileContent + data + "\n";
				}
				System.out.println(fileContent);
				Path path = Paths.get("d:\\mycars.txt");
				Files.write(path, fileContent.getBytes());
				break;
			}
			case 3: {
				CarOrder cc = new CarOrder();
				LocalDate ldd = LocalDate.now();

				Date daa = Date.valueOf(ldd);
				CarOrderImp co = new CarOrderImp();

				System.out.println("enter the Delivery date");
				String dat = sc.next();
				ArrayList<CarOrder> al = co.getDeliveryCarDet(dat);
				for (CarOrder c : al) {
					LocalDate deliveredDate = c.getDeliveredDate().toLocalDate();
					long days = Duration.between(ldd.atTime(0, 0), deliveredDate.atTime(0, 0)).toDays();

					System.out.println(c.getCarName() + " " + deliveredDate + " " + c.getBuyerName());
					if (deliveredDate.isBefore(ldd)) {
						// LocalDate ld1=c.getDeliveredDate().toLocalDate();
						// LocalDate d=ldd.minus(1,ChronoUnit.DAYS);

					} else if (deliveredDate.isAfter(ldd)) {
						// Date dd=new Date((c.deliveredDate).getTime()-daa.getTime());
						// java.util.Date ddate=new Date(deliveredDate.getTime());
						// java.util.Date currentDate=new java.util.Date();
						System.out.println(days + "  more days!!!");

					} else {
						System.out.println("Delivered today!!!!");
					}
				}

				break;
			}
			default:
				System.out.println("enter the valid option");
				break;
			}
			// TestCarOrder.callOrder();
			// TestCarOrder.addCarInFile();
			// TestCarOrder.callGetDelivery();
			// TestCarOrder.callupdate();
			System.out.println("Do you want to continue Y/n");
			m = sc.next().charAt(0);
		} while (m == 'y' || m == 'Y');
	}

	/*
	 * private static void callGetDelivery() throws Exception {
	 * 
	 * } /*public static void callupdate()throws Exception { CarOrderImp obj=new
	 * CarOrderImp(); Scanner sc=new Scanner(System.in);
	 * System.out.println("enter the car number"); int carid=sc.nextInt(); CarDetail
	 * cd=new CarDetail(); cd.carId=carid; obj.updateCarStatus(cd.carId);
	 * 
	 * 
	 * }
	 */
}
