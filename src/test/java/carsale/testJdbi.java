package carsale;

import java.util.List;
import java.util.Scanner;

import org.jdbi.v3.core.Jdbi;

import com.chainsys.carsale.dao.CarOwnerDAO;
import com.chainsys.carsale.dao.impl.CarOwnerImp;
import com.chainsys.carsale.logger.Logger;
import com.chainsys.carsale.model.CarOrder;
import com.chainsys.carsale.model.CarOwner;
import com.chainsys.carsale.util.ConnectionUtil;
import com.chainsys.carsale.util.DbException;

public class testJdbi {
	private static final Logger log = Logger.getInstance();

	static Jdbi jdbi=ConnectionUtil.getJdbi();
   static CarOwnerDAO co=jdbi.onDemand(CarOwnerDAO.class);

public static void main(String[] args) throws DbException
{
	CarOwner coo=new CarOwner();
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the mobile no");
	long mobileno=sc.nextLong();
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
	co.addCarOwner(coo);

	}
}