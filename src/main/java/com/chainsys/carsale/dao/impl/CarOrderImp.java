package com.chainsys.carsale.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.carsale.dao.CarOrderDAO;
import com.chainsys.carsale.logger.Logger;
import com.chainsys.carsale.model.CarOrder;
import com.chainsys.carsale.util.ConnectionUtil;
import com.chainsys.carsale.util.DbException;

public class CarOrderImp implements CarOrderDAO {
	private static final Logger log = Logger.getInstance();
	static final String seller_id = "seller_id";
	private static final String buyer_name = "buyer_name";
	private static final String order_id = "order_id";
	private static final String delivered_date = "delivered_date";
	private static final String car_name = "car_name";
	private static final String car_id = "car_id";
	private static final String  user_id="user_id";
	private static final String  address1="address1";
	private static final String  address2="address2";
	private static final String  buyerState="buyer_state";
	private static final String  pincode="pincode";
	private static final String  city="city";
	private static final String  orderDate="ordered_date";
	private static final String  testDrive="test_drive";
	private static final String  statuss="status";
	public void orderCar(CarOrder carOrder) throws DbException  {
		String check = " select car_id  from car_detail where car_id = ?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(check);) {

			ps.setInt(1, carOrder.getCarId());
			try (ResultSet rs = ps.executeQuery();) {

				if (rs.next()) {

					int carId = rs.getInt(car_id);
					String sql = "insert into car_order(order_id,buyer_name,buyer_contact_number,car_id,seller_id,test_drive,address1,address2,city,buyer_state,pincode,user_id)values(order_id_sq.nextval,?,?,?,?,?,?,?,?,?,?,?)";
					System.out.println(sql);
					try (Statement stt = con.createStatement(); PreparedStatement pst = con.prepareStatement(sql);) {
						pst.setString(1, carOrder.getBuyerName());
						pst.setLong(2, carOrder.getBuyerContactNo());
						pst.setInt(3, carId);
						pst.setInt(4, carOrder.getSellerId());
						pst.setString(5, carOrder.getTestDrive());
						pst.setString(6, carOrder.getAddress1());
						pst.setString(7, carOrder.getAddress2());
						pst.setString(8, carOrder.getCity());
						pst.setString(9, carOrder.getBuyerState());
						pst.setInt(10, carOrder.getPincode());
						pst.setInt(11, carOrder.getUserId());
						int rows = pst.executeUpdate();
						System.out.println(rows);
						System.out.println(sql);

						String upsql = "update car_detail  set status='not available' where car_id=?";
						try (PreparedStatement pt = con.prepareStatement(upsql);)

						{
							pt.setInt(1, carOrder.getCarId());
							int uprs = pt.executeUpdate();
							System.out.println(uprs);
						}
					}
				}
			}
		}

		catch ( SQLException e) {
			log.error(e);

		}
	}

	public List<CarOrder> getCarDeleveryDate(int orderId) throws DbException {
		// TODO Auto-generated method stub
		List<CarOrder> lt = new ArrayList<CarOrder>();
		String sql = "select buyer_name ,order_id,car_id,delivered_date from car_order where order_id=?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, orderId);
			try (ResultSet rs = pst.executeQuery();) {

				while (rs.next()) {
					CarOrder c = new CarOrder();
					c.setBuyerName(rs.getString(buyer_name));
					c.setOrderId(rs.getInt(order_id));
					c.setCarId(rs.getInt(car_id));
					c.setDeliveredDate(rs.getDate(delivered_date));
					lt.add(c);
				}
			}
		} catch (SQLException e) {
			log.error(e);
		}

		return lt;
	}

	public List<CarOrder> getDeliveryCarDet(int orderId) throws DbException {
		// TODO Auto-generated method stub
		// Connection con =null;
		// PreparedStatement pst=null;
		String sql = "select c.car_name ,d.delivered_date,d.buyer_name from car_order d,car_detail c where order_id=?  and c.car_id=d.car_id ";

		List<CarOrder> ts = new ArrayList<CarOrder>();
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			// LocalDate ld=LocalDate.parse(deliver);
			// Date da=Date.valueOf(ld);
			// pst.setDate(1,da);
			pst.setInt(1, orderId);

			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					CarOrder cc = new CarOrder();
					cc.setCarName(rs.getString(car_name));
					cc.setDeliveredDate(rs.getDate(delivered_date));
					cc.setBuyerName(rs.getString(buyer_name));
					ts.add(cc);
				}
				System.out.println(sql);
			}
		} catch (SQLException e) {
			log.error(e);
			}

		return ts;
	}

	@Override
	public void updateCarStatus(int carId) throws DbException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CarOrder> getOrderedCar(int userId) throws DbException {
		String sql="select * from car_order where user_id=?";
		List<CarOrder> ts = new ArrayList<CarOrder>();
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1,userId);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					CarOrder cc = new CarOrder();
				//	cc.setCarName(rs.getString(car_name));
					cc.setDeliveredDate(rs.getDate(delivered_date));
					cc.setBuyerName(rs.getString(buyer_name));
					cc.setOrderId(rs.getInt(order_id));
					cc.setCarId(rs.getInt(car_id));
					cc.setSellerId(rs.getInt(seller_id));
				
					ts.add(cc);
				}
				System.out.println(sql);
			}
		} catch (SQLException e) {
			log.error(e);
			}
		
		return ts;
	}
	public List<CarOrder> getOrderedUserCar(int sellerId)throws DbException
	{
		String sql="select * from car_order where seller_id=?";
		List<CarOrder> ts = new ArrayList<CarOrder>();
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1,sellerId);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					CarOrder cc = new CarOrder();
				//	cc.setCarName(rs.getString(car_name));
					cc.setDeliveredDate(rs.getDate(delivered_date));
					cc.setBuyerName(rs.getString(buyer_name));
					cc.setAddress1(rs.getString(address1));
					cc.setAddress1(rs.getString(address1));
					cc.setOrderId(rs.getInt(order_id));
					cc.setBuyerState(rs.getString(buyerState));
					cc.setCity(rs.getString(city));
					cc.setStatus(rs.getString(statuss));
					cc.setCarId(rs.getInt(car_id));
					cc.setSellerId(rs.getInt(seller_id));
				    cc.setUserId(rs.getInt(user_id));
				    cc.setDeliveredDate(rs.getDate(delivered_date));
				    cc.setOrderedDate(rs.getDate(orderDate));
				    cc.setTestDrive(rs.getString(testDrive));
				    cc.setPincode(rs.getInt(pincode));
					ts.add(cc);
				}
				System.out.println(sql);
			}
		} catch (SQLException e) {
			log.error(e);
			}
		return ts;
		
	}

	/*
	 * public void updateCarStatus(int carId) throws DbException { // TODO
	 * Auto-generated method stub CarDetail c=new CarDetail(); Connection
	 * con=ConnectionUtil.getConnection(); String
	 * sql="update car_detail  set status='not available' where car_id=(select car_id from car_order where car_order.car_id=cardetail.?"
	 * ; PreparedStatement pst=con.prepareStatement(sql); pst.setInt(1,c.carId); int
	 * row=pst.executeUpdate(); System.out.println(row); }
	 */

}
