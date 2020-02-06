package com.chainsys.carsale.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.chainsys.carsale.dao.CarDetailDAO;
import com.chainsys.carsale.model.CarDetail;
import com.chainsys.carsale.model.CarOwner;
import com.chainsys.carsale.util.TestConnection;

public class CarDetailImp implements CarDetailDAO {
	public int carSellerId;
	public String password;
	public Long contactNo;
	public int sellerId;

	public int getSellerId(Long mobileNo, String password) throws Exception {

		String sql = "select seller_id from car_seller where seller_contact_no=?  and user_password=?";
		try (Connection con = TestConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setLong(1, mobileNo);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					sellerId = rs.getInt("seller_id");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sellerId;

	}

	public int verifyUser(int sellerIdd, String password) throws Exception {

		String sql = "select seller_id from car_seller where seller_id=?  and user_password=?";
			try(Connection con = TestConnection.getConnection();PreparedStatement pst = con.prepareStatement(sql);)
			{
			pst.setInt(1, sellerIdd);
			pst.setString(2, password);
			try(ResultSet rs = pst.executeQuery();)
			{
				if (rs.next()) {
				sellerId = rs.getInt("seller_id");
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return sellerId;

	}

	public void addCarDetail(CarDetail cardetail) throws Exception {
		// TODO Auto-generated method stub
		// Scanner s = new Scanner(System.in);

		Connection con = null;
		PreparedStatement pst = null;
		PreparedStatement pstt = null;
		ResultSet rs=null;

		try {
			con = TestConnection.getConnection();
			String query = "select seller_id,seller_contact_no,user_password from car_seller where user_password= '"
					+ cardetail.getCarOwner().getPassword() + "'";
			
                  if (cardetail.getCarOwnerId() != 0) {
				query = query + " and seller_id=?";
				pst = con.prepareStatement(query);
				pst.setInt(1, cardetail.getCarOwnerId());

			} else if (cardetail.getCarOwner().getContactNo() != 0) {
				query = query + " and  seller_contact_no=?";

				pst = con.prepareStatement(query);
				pst.setLong(1, cardetail.getContactNo());
			}
			System.out.println(query);
			rs = pst.executeQuery();

			if (rs.next()) {
				Date updatedDate = Date.valueOf(cardetail.getUpdateDate());
				String sql = "insert into car_detail(car_seller_id,car_id,car_brand,car_name,tr_type,fuel_type,reg_state,reg_year,driven_km,price,update_date,registration_no,vehicle_identification_no,car_available_city,is_owner)values(?,car_id_sq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				System.out.println(sql);
				// car_seller(seller_id,seller_name,seller_contact_no)values("+sellerId+",'"+ownerName+"',"+contactNO+")";
				pstt = con.prepareStatement(sql);
				// int row=smt.executeUpdate(sql);
				pstt.setInt(1, cardetail.getCarOwnerId());
				pstt.setString(2, cardetail.getCarBrand());
				pstt.setString(3, cardetail.getCarName());
				pstt.setString(4, cardetail.getTrType());
				pstt.setString(5, cardetail.getFuelType());
				pstt.setString(6, cardetail.getRegState());
				pstt.setInt(7, cardetail.getRegYear());
				pstt.setFloat(8, cardetail.getDrivenKm());
				pstt.setFloat(9, cardetail.getPrice());
				pstt.setDate(10, updatedDate);
				// pst.setString(11, cardetail.status);
				pstt.setString(11, cardetail.getRegistrationNo());
				pstt.setString(12, cardetail.getVehicleIdNo());
				pstt.setString(13, cardetail.getCarAvailableCity());
				pstt.setInt(14, cardetail.getIsOwner());
				int rows = pstt.executeUpdate();
				System.out.println(rows);
			} else {
				throw new Exception("Invalid Data");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null)
			{
				rs.close();
			}
			if (pstt != null) {
				pstt.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (con != null) {
				con.close();
			}
		}

		// s.close();

	}

	public ArrayList<CarDetail> getCarDetail(String carName) throws Exception {

		//Connection con = null;
		//PreparedStatement ps = null;
		ArrayList<CarDetail> ar = new ArrayList<CarDetail>();
		String sql = "select * from  car_detail t left outer join car_seller d on t.car_seller_id=d.seller_id where t.car_brand=?";
		
		try (Connection con = TestConnection.getConnection();PreparedStatement ps = con.prepareStatement(sql);)
		{
			ps.setString(1, carName);
			try(ResultSet rs = ps.executeQuery();)
			{
			while (rs.next()) {
				CarDetail c = new CarDetail();
				c.setCarName(rs.getString("car_name"));
				c.setCarBrand(rs.getString("car_brand"));
				c.setTrType(rs.getString("tr_type"));
				c.setFuelType(rs.getString("fuel_type"));
				c.setRegState(rs.getString("reg_state"));
				c.setRegYear(rs.getInt("reg_year"));
				c.setDrivenKm(rs.getInt("driven_km"));
				c.setPrice(rs.getInt("price"));
				c.setStatus(rs.getString("status"));
				c.setRegistrationNo(rs.getString("registration_no"));
				// c.vehicleIdNo=rs.getString("vehicle_identification_no");

				CarOwner carowner = new CarOwner();
				carowner.setownerName(rs.getString("seller_name"));
				c.setCarOwner(carowner);
				ar.add(c);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ar;
	}

	public ArrayList<CarDetail> getCarDetail(String carBrand, String regState) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<CarDetail> ar = new ArrayList<CarDetail>();
		
		String sql = "select * from car_detail where car_brand=? and  reg_state=?";
				try (Connection con = TestConnection.getConnection();PreparedStatement ps = con.prepareStatement(sql);)
				{
					System.out.println(sql);
			
			ps.setString(1, carBrand);
			ps.setString(2, regState);
			try(ResultSet rss = ps.executeQuery();)
			{
			while (rss.next()) {
				CarDetail c = new CarDetail();
				c.setCarName(rss.getString("car_name"));
				c.setCarBrand(rss.getString("car_brand"));
				c.setTrType(rss.getString("tr_type"));
				c.setFuelType(rss.getString("fuel_type"));
				c.setRegState(rss.getString("reg_state"));
				c.setRegYear(rss.getInt("reg_year"));
				c.setDrivenKm(rss.getInt("driven_km"));
				c.setRegistrationNo(rss.getString("registration_no"));
				c.setPrice(rss.getInt("price"));
				c.setStatus(rss.getString("status"));

				ar.add(c);
			}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ar;

	}

	public ArrayList<CarDetail> getDetailWithOwner(String carBrand) throws Exception {
		// TODO Auto-generated method stub
		
		ArrayList<CarDetail> list = new ArrayList<CarDetail>();
		String sql = "select * from  car_detail t left outer join car_seller d on t.car_seller_id=d.seller_id where t.car_brand=?";
		
		try (Connection con = TestConnection.getConnection();PreparedStatement ps = con.prepareStatement(sql);)
{

			System.out.println(sql);
			
			ps.setString(1, carBrand);
		try(	ResultSet rss = ps.executeQuery();)
		{
			while (rss.next()) {
				/*
				 * String trType=rss.getString("tr_type"); String
				 * fuelType=rss.getString("fuel_type"); String
				 * regstate=rss.getString("reg_state"); int regYear=rss.getInt("reg_year"); int
				 * drivenkm=rss.getInt("driven_km"); int price=rss.getInt("price"); String
				 * status=rss.getString("status"); int sellerId=rss.getInt("seller_id"); String
				 * sellername=rss.getString("seller_name"); Long
				 * sellerContact=rss.getLong("seller_contact_no");
				 */
				// 1 row info -> storing in 1 object
				CarDetail cd = new CarDetail();
				cd.setCarId(rss.getInt("car_id"));
				cd.setCarName(rss.getString("car_name"));
				cd.setCarBrand(rss.getString("car_brand"));
				// cd.setCarOwnerId(rss.getInt("car_seller_id"));
				cd.setDrivenKm(rss.getInt("driven_km"));
				cd.setFuelType(rss.getString("fuel_type"));
				cd.setRegState(rss.getString("reg_state"));
				cd.setStatus(rss.getString("status"));
				cd.setRegYear(rss.getInt("reg_year"));
				cd.setTrType(rss.getString("tr_type"));
				cd.setCarAvailableCity(rss.getString("car_available_city"));
				cd.setRegistrationNo(rss.getString("registration_no"));
				cd.setVehicleIdNo(rss.getString("vehicle_identification_no"));
				cd.setPrice(rss.getInt("price"));

				CarOwner carowner = new CarOwner();
				carowner.setownerName(rss.getString("seller_name"));
				carowner.setownerId(rss.getInt("seller_id"));// instread of carowner.ownerid = rss.getInt("seller_id");
				carowner.setContactNo(rss.getLong("seller_contact_no"));

				cd.setCarOwner(carowner);

				// 2. Add each row data to list
				list.add(cd);
				// System.out.println(sellerId+""+sellername+""+sellerContact+""+carName+"
				// "+carBrand+" "+trType+" "+fuelType+" "+regstate+" "+regYear+" "+drivenkm+"
				// "+price+" "+status);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<CarDetail> getUpdatedCar(String Status) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<CarDetail> ar = new ArrayList<CarDetail>();

		//Connection con = null;
		//PreparedStatement pst = null;
		String sql = "select * from car_detail cd, car_seller s where cd.car_seller_id = s.seller_id and cd.status=?";
		try (Connection con = TestConnection.getConnection();PreparedStatement pst = con.prepareStatement(sql);)
		{
			// String sql="Select * from car_detail ,car_owner where Status=(select from car
			// car_owner
			System.out.println(sql);
			
			pst.setString(1, Status);
			try(ResultSet rs = pst.executeQuery();)
			{
			while (rs.next()) {
				CarDetail c = new CarDetail();
				CarOwner co = new CarOwner();
				c.setCarBrand(rs.getString("car_brand"));
				c.setCarName(rs.getString("car_name"));
				c.setTrType(rs.getString("tr_type"));
				c.setDrivenKm(rs.getInt("driven_km"));
				c.setFuelType(rs.getString("fuel_type"));
				c.setRegState(rs.getString("reg_state"));
				c.setRegYear(rs.getInt("reg_year"));
				c.setPrice(rs.getInt("price"));
				c.setStatus(rs.getString("status"));
				c.setCarAvailableCity(rs.getString("car_available_city"));
				c.setRegistrationNo(rs.getString("registration_no"));
				co.setownerName(rs.getString("seller_name"));
				co.setContactNo(rs.getLong("seller_contact_no"));
				co.setownerId(rs.getInt("seller_id"));
				c.setCarOwner(co);
				ar.add(c);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return ar;
	}

	public ArrayList<CarDetail> getCarDetail(String carName, String carBrand, String fuleType) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<CarDetail> getCarDetail(Float max, String carBrand) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<CarDetail> getCarDetailAbovePrice(float min,String carBrand) throws Exception {
		ArrayList<CarDetail> ar=new ArrayList<CarDetail>();
		float max=900000000;
		 String car_status="available";
		String sql="select car_brand,car_name,car_id,Driven_km,price,fuel_type,car_available_city,registration_no,tr_type,reg_year from car_detail where price between ? and ? and car_brand IN(?) and status=? order by price asc";
		try(Connection con=TestConnection.getConnection();PreparedStatement ps=con.prepareStatement(sql))
		{
			ps.setFloat(1, min);
			ps.setFloat(2,max);
			ps.setString(3, carBrand);
			ps.setString(4, car_status);
			try(ResultSet rs=ps.executeQuery();)
			{
				while(rs.next())
				{
				  CarDetail carDetail=new CarDetail();
				  carDetail.setCarBrand(rs.getString("car_brand"));
                  carDetail	.setCarName(rs.getString("car_name"));
                  carDetail.setCarId(rs.getInt("car_id"));
                  carDetail.setDrivenKm(rs.getInt("Driven_km"));
				  carDetail.setPrice(rs.getInt("price"));
				  carDetail.setFuelType(rs.getString("fuel_type"));
				  carDetail.setCarAvailableCity(rs.getString("car_available_city"));
				  carDetail.setRegistrationNo(rs.getString("registration_no"));
				  carDetail.setTrType(rs.getString("tr_type"));
				  carDetail.setRegYear(rs.getInt("reg_year"));
				  ar.add(carDetail);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return ar;
	}

	@Override
	public ArrayList<CarDetail> getCarDetailBelowPrice(Float max, String carBrand) throws Exception {
		ArrayList<CarDetail> ar=new ArrayList<CarDetail>();
	   String carStatus="available";
		String sql="select car_brand,car_name,reg_year,car_id,Driven_km,price,fuel_type,car_available_city,registration_no,tr_type from car_detail where price<=? and  car_brand IN(?) and status=? order by price asc";
		try(Connection con=TestConnection.getConnection();PreparedStatement ps=con.prepareStatement(sql))
		{
			ps.setFloat(1,max);
			ps.setString(2, carBrand);
			ps.setString(3, carStatus);
			try(ResultSet rs=ps.executeQuery();)
			{
				while(rs.next())
				{
				  CarDetail carDetail=new CarDetail();
				  carDetail.setCarBrand(rs.getString("car_brand"));
                  carDetail	.setCarName(rs.getString("car_name"));
                  carDetail.setCarId(rs.getInt("car_id"));
                  carDetail.setDrivenKm(rs.getInt("Driven_km"));
				  carDetail.setPrice(rs.getInt("price"));
				  carDetail.setFuelType(rs.getString("fuel_type"));
				  carDetail.setCarAvailableCity(rs.getString("car_available_city"));
				  carDetail.setRegistrationNo(rs.getString("registration_no"));
				  carDetail.setTrType(rs.getString("tr_type"));
				  carDetail.setRegYear(rs.getInt("reg_year"));
				  ar.add(carDetail);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return ar;}

	
	@Override
	public ArrayList<CarDetail> getCarDetailAboveDrivenKm(float startFrom, float endTo) throws Exception {
		ArrayList<CarDetail> ar=new ArrayList<CarDetail>();
		String carStatus="available";
		String sql="select car_brand,car_name,reg_year,car_id,Driven_km,price,fuel_type,car_available_city,registration_no,tr_type from car_detail where driven_km between ? and ?  and status=? order by driven_km asc";
		try(Connection con=TestConnection.getConnection();PreparedStatement ps=con.prepareStatement(sql))
		{
			ps.setFloat(1,startFrom);
			ps.setFloat(2, endTo);
			ps.setString(3, carStatus);
			try(ResultSet rs=ps.executeQuery();)
			{
				while(rs.next())
				{
				  CarDetail carDetail=new CarDetail();
				  carDetail.setCarBrand(rs.getString("car_brand"));
                  carDetail	.setCarName(rs.getString("car_name"));
                  carDetail.setCarId(rs.getInt("car_id"));
                  carDetail.setDrivenKm(rs.getInt("Driven_km"));
				  carDetail.setPrice(rs.getInt("price"));
				  carDetail.setFuelType(rs.getString("fuel_type"));
				  carDetail.setCarAvailableCity(rs.getString("car_available_city"));
				  carDetail.setRegistrationNo(rs.getString("registration_no"));
				  carDetail.setTrType(rs.getString("tr_type"));
				  carDetail.setRegYear(rs.getInt("reg_year"));
				  ar.add(carDetail);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return ar;}

	@Override
	public ArrayList<CarDetail> getCarDetailUseFuelType(String fuelType) throws Exception {
		ArrayList<CarDetail> ar=new ArrayList<CarDetail>();
		String sql="select car_brand,car_name,reg_year,car_id,Driven_km,price,fuel_type,car_available_city,registration_no,tr_type from car_detail where fuel_type=?";
		try(Connection con=TestConnection.getConnection();PreparedStatement ps=con.prepareStatement(sql))
		{
			ps.setString(1,fuelType);
			try(ResultSet rs=ps.executeQuery();)
			{
				while(rs.next())
				{
				  CarDetail carDetail=new CarDetail();
				  carDetail.setCarBrand(rs.getString("car_brand"));
                  carDetail	.setCarName(rs.getString("car_name"));
                  carDetail.setCarId(rs.getInt("car_id"));
                  carDetail.setDrivenKm(rs.getInt("Driven_km"));
				  carDetail.setPrice(rs.getInt("price"));
				  carDetail.setFuelType(rs.getString("fuel_type"));
				  carDetail.setCarAvailableCity(rs.getString("car_available_city"));
				  carDetail.setRegistrationNo(rs.getString("registration_no"));
				  carDetail.setTrType(rs.getString("tr_type"));
				  carDetail.setRegYear(rs.getInt("reg_year"));
				  ar.add(carDetail);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return ar;
	}

}
