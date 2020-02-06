package com.chainsys.carsale.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.chainsys.carsale.dao.CarOwnerDAO;
import com.chainsys.carsale.model.CarDetail;
import com.chainsys.carsale.model.CarOrder;
import com.chainsys.carsale.model.CarOwner;
import com.chainsys.carsale.util.TestConnection;

public class CarOwnerImp implements CarOwnerDAO {

	public boolean isCarOwnerAlreadyRegistered(Long mobileNo) throws Exception {
		boolean exists = false;
		// Connection con=null;
		String sqll = "select * from car_seller where seller_contact_no=?";

		// Statement st=null;
		try (Connection con = TestConnection.getConnection(); Statement st = con.createStatement();) {
			PreparedStatement ps = con.prepareStatement(sqll);
			ps.setLong(1, mobileNo);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					exists = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}

	public void AddCarOwner(CarOwner carOwner) throws Exception {
		// Connection con=null;
		// PreparedStatement pst=null;
		String sql = "insert into car_seller(seller_id,seller_name,seller_contact_no,user_password,address1,address2,city,seller_state,pincode)values(seller_id_sq.nextval,?,?,?,?,?,?,?,?)";

		try (Connection con = TestConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setString(1, carOwner.getownerName());
			pst.setLong(2, carOwner.getContactNo());
			pst.setString(3, carOwner.getPassword());
			pst.setString(4, carOwner.getAddress1());
			pst.setString(5, carOwner.getAddress2());
			pst.setString(6, carOwner.getCity());
			pst.setString(7, carOwner.getState());
			pst.setInt(8, carOwner.getPincode());

			int row = pst.executeUpdate();
			System.out.println(row);
			System.out.println(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteCarDetail(int carOwnerId, int carId) throws Exception {
		// TODO Auto-generated method stub
		// Connection con=null;
		// PreparedStatement ps=null;
		// PreparedStatement pst=null;
		String sqll = "select seller_id from car_seller where seller_id=?";

		try (Connection con = TestConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sqll);) {
			ps.setInt(1, carOwnerId);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					int carSellerId = rs.getInt("seller_id");
					String sql = "delete from  car_detail where car_id=? and car_seller_id=? and lower(status)='available'";
					try (PreparedStatement pst = con.prepareStatement(sql);) {
						pst.setInt(1, carId);
						pst.setInt(2, carSellerId);
						int rows = pst.executeUpdate();
						System.out.println(rows);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<CarOwner> ViewYourCar(long mobileNo) throws Exception {
		ArrayList<CarOwner> al = new ArrayList<CarOwner>();
		// Connection con=null;
		// PreparedStatement ps=null;
		String sql = "select * from car_seller s,car_detail c where s.seller_contact_no=? and s.seller_id=c.car_seller_id";
		try (Connection con = TestConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);)

		{
			// String sql="select seller_id from car_seller where
			// seller_contact_no="+mobileno+"";
			// Statement st=con.createStatement();

			ps.setLong(1, mobileNo);

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					CarOwner c = new CarOwner();
					CarDetail cd = new CarDetail();
					c.setownerName(rs.getString("seller_name"));
					c.setownerId(rs.getInt("seller_id"));
					cd.setCarAvailableCity(rs.getString("car_available_city"));
					cd.setCarBrand(rs.getString("car_brand"));
					cd.setCarName(rs.getString("car_name"));
					cd.setDrivenKm(rs.getInt("Driven_km"));
					cd.setPrice(rs.getInt("price"));
					cd.setRegistrationNo(rs.getString("registration_no"));
					cd.setRegYear(rs.getInt("reg_year"));
					cd.setVehicleIdNo(rs.getString("vehicle_identification_no"));
					c.setCarDetail(cd);
					al.add(c);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return al;
	}

	public void updateCarPrice(CarOwner carOwner) throws Exception {

		// Connection con = null;
		// Statement st = null;
		// PreparedStatement ps = null;
		String sql = null;
		CarDetail cardetail = carOwner.getCarDetail();
		try (Connection con = TestConnection.getConnection();) {

			if (carOwner.getownerId() != 0) {
				// sql = "update car_detail cd set cd.price="+cardetail.getPrice()+"where car_id
				// = "+cardetail.getCarId()+" and ( car_seller_id = "+carOwner.getownerId()+" or
				// car_seller_id in ( select seller_id from car_seller where seller_contact_no
				// ="+carOwner.getcontactNo()+"";
				sql = "update car_detail cd set cd.price=? where car_id =? and car_seller_id=?";
				try (PreparedStatement ps = con.prepareStatement(sql);) {
					ps.setFloat(1, cardetail.getPrice());
					ps.setInt(2, cardetail.getCarId());
					ps.setInt(3, carOwner.getownerId());
					int rs = ps.executeUpdate();
					System.out.println(rs + "Updated successFully");
				}
			} else if (carOwner.getContactNo() != 0) {
				sql = "update car_detail cd set cd.price=? where car_id =? and car_seller_id in ( select seller_id from car_seller where seller_contact_no =?)";
				try (PreparedStatement ps = con.prepareStatement(sql);) {
					ps.setFloat(1, cardetail.getPrice());
					ps.setInt(2, cardetail.getCarId());
					ps.setLong(3, carOwner.getContactNo());
					int rs = ps.executeUpdate();
					System.out.println(rs + "Updated successFully");
				}
			} else {
				System.out.println("Failed to Upadate");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<CarOrder> ViewYourPlacedCar(Long mobileNo) throws Exception {
		//Connection con = null;
		//PreparedStatement ps = null;
		ArrayList<CarOrder> ar = new ArrayList<CarOrder>();
		String sql = "select * from car_order where seller_id=(select seller_id from car_seller where seller_contact_no=?)";

		try (Connection	con = TestConnection.getConnection();PreparedStatement ps = con.prepareStatement(sql);)
		{
			
			ps.setLong(1, mobileNo);
			try(ResultSet rs = ps.executeQuery();)
			{
			if (rs.next()) {
				CarOrder co = new CarOrder();
				co.setBuyerContactNo(rs.getLong("buyer_contact_number"));
				co.setBuyerName(rs.getString("buyer_name"));
				co.setCarId(rs.getInt("car_id"));
				co.setOrderId(rs.getInt("order_id"));
				co.setTestDrive(rs.getString("test_drive"));
				co.setDeliveredDate(rs.getDate("delivered_date"));
				co.setOrderedDate(rs.getDate("ordered_date"));
				co.setAddress1(rs.getString("address1"));
				co.setAddress2(rs.getString("address2"));
				co.setBuyerState(rs.getString("buyer_state"));
				co.setStatus(rs.getString("status"));
				co.setPincode(rs.getInt("pincode"));
				ar.add(co);
			} else {
				System.out.println("your car not ordered");
			}
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	
		return ar;
	}
}

