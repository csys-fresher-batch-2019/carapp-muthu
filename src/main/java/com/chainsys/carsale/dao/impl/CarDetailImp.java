package com.chainsys.carsale.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.carsale.dao.CarDetailDAO;
import com.chainsys.carsale.logger.Logger;
import com.chainsys.carsale.model.CarDetail;
import com.chainsys.carsale.model.CarOwner;
import com.chainsys.carsale.util.ConnectionUtil;
import com.chainsys.carsale.util.DbException;

public class CarDetailImp implements CarDetailDAO {
	private static final Logger log = Logger.getInstance();
	private static final String seller_id = "seller_id";
	private static final String seller_contact_no = "seller_contact_no";
	private static final String car_name = "car_name";
	private static final String car_id = "car_id";
	private static final String car_brand = "car_brand";
	private static final String tr_type = "tr_type";
	private static final String fuel_type = "fuel_type";
	private static final String registration_no = "registration_no";
	private static final String seller_name = "seller_name";
	private static final String reg_year = "reg_year";
	private static final String reg_state = "reg_state";
	private static final String price = "price";
	private static final String statuss = "status";
	private static final String driven_km = "driven_km";
	private static final String car_available_city = "car_available_city";
	private static final String vehicle_identification_no = "vehicle_identification_no";
    private static final String image="images";
	public int getSellerId(Long mobileNo, String password) throws DbException {

		int sellerId = 0;
		String sql = "select seller_id from car_seller where (seller_contact_no=? or seller_id=?) and user_password=?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setLong(1, mobileNo);
			ps.setLong(2, mobileNo);
			ps.setString(3, password);
			try (ResultSet rs = ps.executeQuery();) {

				if (rs.next()) {
					sellerId = rs.getInt(seller_id);
					log.info("login success");
				} else {
					log.info("login failed");

				}
			}
		} catch (SQLException e) {
			log.error(e);

		}
		return sellerId;

	}

	/*public int verifyUser(int sellerIdd, String password) throws DbException {
		int sellerId = 0;
		String sql = "select seller_id from car_seller where seller_id=?  and user_password=?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			pst.setInt(1, sellerIdd);
			pst.setString(2, password);

			try (ResultSet rs = pst.executeQuery();) {
				if (rs.next()) {
					sellerId = rs.getInt(seller_id);
					log.info("Login success");
				}
			}
		} catch (SQLException e) {
			log.error(e);
			throw new DbException("invalid User");
		}

		return sellerId;

	}*/

	public int getSellerId2(CarDetail cardetail) throws SQLException {

		int sellerId = 0;
		String query = "select seller_id,seller_contact_no,user_password from car_seller where user_password= ? ";
		if (cardetail.getCarOwnerId() != 0) {
			query = query + " and seller_id=?";
		} else if (cardetail.getCarOwner().getContactNo() != 0) {
			query = query + " and  seller_contact_no=?";
		}
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(query);) {
			pst.setString(1, cardetail.getCarOwner().getPassword());
			if (cardetail.getCarOwnerId() != 0) {

				pst.setInt(2, cardetail.getCarOwnerId());

			} else if (cardetail.getCarOwner().getContactNo() != 0) {

				pst.setLong(2, cardetail.getContactNo());
			}
			System.out.println(query);

			try (ResultSet rs = pst.executeQuery();) {

				if (rs.next()) {
					sellerId = rs.getInt("seller_id");

				}
			}

		}
		return sellerId;

	}

	public void addCarDetail(CarDetail cardetail) throws DbException {

		try {
			LocalDate updatedDate=LocalDate.now();
				Date updatedDate1 = Date.valueOf(updatedDate);
				String sql = "insert into car_detail(car_seller_id,car_id,car_brand,car_name,tr_type,fuel_type,reg_state,reg_year,driven_km,price,update_date,registration_no,vehicle_identification_no,car_available_city,is_owner,images)values(?,car_id_sq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				System.out.println(sql);
				try (Connection con = ConnectionUtil.getConnection();
						PreparedStatement pstt = con.prepareStatement(sql);) {
					pstt.setInt(1, cardetail.getCarOwnerId());
					pstt.setString(2, cardetail.getCarBrand());
					pstt.setString(3, cardetail.getCarName());
					pstt.setString(4, cardetail.getTrType());
					pstt.setString(5, cardetail.getFuelType());
					pstt.setString(6, cardetail.getRegState());
					pstt.setInt(7, cardetail.getRegYear());
					pstt.setFloat(8, cardetail.getDrivenKm());
					pstt.setFloat(9, cardetail.getPrice());
					pstt.setDate(10, updatedDate1);
					pstt.setString(11, cardetail.getRegistrationNo());
					pstt.setString(12, cardetail.getVehicleIdNo());
					pstt.setString(13, cardetail.getCarAvailableCity());
					pstt.setInt(14, cardetail.getIsOwner());
					pstt.setString(15, cardetail.getImageSrc());
					int rows = pstt.executeUpdate();
					System.out.println(rows);
				}

			
		} catch (SQLException e) {
			log.error(e);
			throw new DbException("unable to add car");
		}

	}

	// s.close();

	public List<CarDetail> getCarDetail(String carName) throws DbException {

		List<CarDetail> ar = new ArrayList<CarDetail>();
		String stat="available";
		String sql = "select * from  car_detail t left outer join car_seller d on t.car_seller_id=d.seller_id where t.car_brand=? and t.status=?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, carName);
			ps.setString(2, stat);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					CarDetail c = new CarDetail();
					c.setCarId(rs.getInt(car_id));
					c.setCarName(rs.getString(car_name));
					c.setCarBrand(rs.getString(car_brand));
					c.setTrType(rs.getString(tr_type));
					c.setFuelType(rs.getString(fuel_type));
					c.setRegState(rs.getString(reg_state));
					c.setRegYear(rs.getInt(reg_year));
					c.setDrivenKm(rs.getInt(driven_km));
					c.setPrice(rs.getInt(price));
					c.setStatus(rs.getString(statuss));
					c.setRegistrationNo(rs.getString(registration_no));
					c.setImageSrc(rs.getString(image));
					CarOwner carowner = new CarOwner();
					carowner.setownerName(rs.getString(seller_name));
					c.setCarOwner(carowner);
					ar.add(c);
				}
			}
		} catch (SQLException e) {
			log.error(e);
			throw new DbException("unable to retrive the car information!!");
		}

		return ar;
	}

	public List<CarDetail> getCarDetail(String carBrand, String regState) throws DbException {
		// TODO Auto-generated method stub
		List<CarDetail> ar = new ArrayList<CarDetail>();

		String sql = "select * from car_detail where car_brand=? and  reg_state=?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			System.out.println(sql);

			ps.setString(1, carBrand);
			ps.setString(2, regState);
			try (ResultSet rss = ps.executeQuery();) {
				while (rss.next()) {
					CarDetail c = new CarDetail();
					c.setCarName(rss.getString(car_name));
					c.setCarBrand(rss.getString(car_brand));
					c.setTrType(rss.getString(tr_type));
					c.setFuelType(rss.getString(fuel_type));
					c.setRegState(rss.getString(reg_state));
					c.setRegYear(rss.getInt(reg_year));
					c.setDrivenKm(rss.getInt(driven_km));
					c.setRegistrationNo(rss.getString(registration_no));
					c.setPrice(rss.getInt(price));
					c.setStatus(rss.getString(statuss));

					ar.add(c);
				}
			}
		} catch (SQLException e) {
			log.error(e);
			throw new DbException("unable to retrive car Details");
		}
		return ar;

	}

	public List<CarDetail> getDetailWithOwner(String carBrand) throws DbException {

		List<CarDetail> list = new ArrayList<CarDetail>();
		String sql = "select * from  car_detail t left outer join car_seller d on t.car_seller_id=d.seller_id where t.car_brand=?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			System.out.println(sql);

			ps.setString(1, carBrand);
			try (ResultSet rss = ps.executeQuery();) {
				while (rss.next()) {

					// 1 row info -> storing in 1 object
					CarDetail cd = new CarDetail();
					cd.setCarId(rss.getInt(car_id));
					cd.setCarName(rss.getString(car_name));
					cd.setCarBrand(rss.getString(car_brand));
					// cd.setCarOwnerId(rss.getInt("car_seller_id"));
					cd.setDrivenKm(rss.getInt(driven_km));
					cd.setFuelType(rss.getString(fuel_type));
					cd.setRegState(rss.getString(reg_state));
					cd.setStatus(rss.getString(statuss));
					cd.setRegYear(rss.getInt(reg_year));
					cd.setTrType(rss.getString(tr_type));
					cd.setCarAvailableCity(rss.getString(car_available_city));
					cd.setRegistrationNo(rss.getString(registration_no));
					cd.setVehicleIdNo(rss.getString(vehicle_identification_no));
					cd.setPrice(rss.getInt(price));

					CarOwner carowner = new CarOwner();
					carowner.setownerName(rss.getString(seller_name));
					carowner.setownerId(rss.getInt(seller_id));// instread of carowner.ownerid =

					carowner.setContactNo(rss.getLong(seller_contact_no));

					cd.setCarOwner(carowner);

					// 2. Add each row data to list
					list.add(cd);
				}
			}
		} catch (SQLException e) {
			log.error(e);
			throw new DbException("unable to retrive");
		}

		return list;
	}

	public List<CarDetail> getUpdatedCar(String status) throws DbException {

		List<CarDetail> ar = new ArrayList<CarDetail>();

		String sql = "select * from car_detail cd, car_seller s where cd.car_seller_id = s.seller_id and cd.status=?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {
			System.out.println(sql);

			pst.setString(1, status);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					CarDetail c = new CarDetail();
					CarOwner co = new CarOwner();
					c.setCarBrand(rs.getString(car_brand));
					c.setCarName(rs.getString(car_name));
					c.setTrType(rs.getString(tr_type));
					c.setDrivenKm(rs.getInt(driven_km));
					c.setFuelType(rs.getString(fuel_type));
					c.setRegState(rs.getString(reg_state));
					c.setRegYear(rs.getInt(reg_year));
					c.setPrice(rs.getInt(price));
					c.setStatus(rs.getString(statuss));
					c.setCarId(rs.getInt(car_id));
					c.setCarAvailableCity(rs.getString(car_available_city));
					c.setRegistrationNo(rs.getString(registration_no));
					co.setownerName(rs.getString(seller_name));
					co.setContactNo(rs.getLong(seller_contact_no));
					co.setownerId(rs.getInt(seller_id));
					c.setCarOwner(co);
					ar.add(c);
				}
			}
		} catch (SQLException e) {
			log.error(e);
		}

		return ar;
	}

	public List<CarDetail> getCarDetail(String carName, String carBrand, String fuleType) throws DbException {

		return null;
	}

	@Override
	public List<CarDetail> getCarDetail(Float max, String carBrand) throws DbException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarDetail> getCarDetailAbovePrice(float min) throws DbException {
		List<CarDetail> ar = new ArrayList<CarDetail>();
		float max = 900000000;
		String car_status = "available";
		String sql="select * from  car_detail t left outer join car_seller d on t.car_seller_id=d.seller_id  where price between ? and ?  and status=?";

		//String sql = "select car_brand,images,car_name,car_id,driven_km,price,fuel_type,car_available_city,registration_no,tr_type,reg_year from car_detail where price between ? and ?  and status=? order by price asc";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setFloat(1, min);
			ps.setFloat(2, max);
			ps.setString(3, car_status);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
			
					CarDetail carDetail = new CarDetail();
					carDetail.setCarOwnerId(rs.getInt(seller_id));
					carDetail.setCarBrand(rs.getString(car_brand));
					carDetail.setCarName(rs.getString(car_name));
					carDetail.setCarId(rs.getInt(car_id));
					carDetail.setDrivenKm(rs.getInt(driven_km));
					carDetail.setPrice(rs.getInt(price));
					carDetail.setFuelType(rs.getString(fuel_type));
					carDetail.setCarAvailableCity(rs.getString(car_available_city));
					carDetail.setRegistrationNo(rs.getString(registration_no));
					carDetail.setTrType(rs.getString(tr_type));
					carDetail.setRegYear(rs.getInt(reg_year));
					carDetail.setImageSrc(rs.getString(image));
					ar.add(carDetail);
				}
			}
		} catch (Exception e) {
			log.error(e);
			throw new DbException("unable to retrive");

		}
		return ar;
	}

	@Override
	public List<CarDetail> getCarDetailBelowPrice(Float max) throws DbException {
		List<CarDetail> ar = new ArrayList<CarDetail>();
		String carStatus = "available";
		String sql="select * from  car_detail t left outer join car_seller d on t.car_seller_id=d.seller_id  where price <=? and status=?";

		//String sql = "select car_brand,images,car_name,reg_year,car_id,driven_km,price,fuel_type,car_available_city,registration_no,tr_type from car_detail where price<=? and status=? order by price asc";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setFloat(1, max);
			ps.setString(2, carStatus);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					CarDetail carDetail = new CarDetail();
					carDetail.setCarOwnerId(rs.getInt(seller_id));
					carDetail.setCarBrand(rs.getString(car_brand));
					carDetail.setCarName(rs.getString(car_name));
					carDetail.setCarId(rs.getInt(car_id));
					carDetail.setDrivenKm(rs.getInt(driven_km));
					carDetail.setPrice(rs.getInt(price));
					carDetail.setFuelType(rs.getString(fuel_type));
					carDetail.setCarAvailableCity(rs.getString(car_available_city));
					carDetail.setRegistrationNo(rs.getString(registration_no));
					carDetail.setTrType(rs.getString(tr_type));
					carDetail.setRegYear(rs.getInt(reg_year));
					carDetail.setImageSrc(rs.getString(image));
					ar.add(carDetail);
				}
			}
		} catch (SQLException e) {
			log.error(e);

			throw new DbException("unable to retrive");

		}
		return ar;
	}

	@Override
	public List<CarDetail> getCarDetailAboveDrivenKm(float startFrom, float endTo) throws DbException {
		List<CarDetail> ar = new ArrayList<CarDetail>();
		String carStatus = "available";
		String sql = "select car_brand,car_name,reg_year,car_id,driven_km,price,fuel_type,car_available_city,registration_no,tr_type from car_detail where driven_km between ? and ?  and status=? order by driven_km asc";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setFloat(1, startFrom);
			ps.setFloat(2, endTo);
			ps.setString(3, carStatus);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					CarDetail carDetail = new CarDetail();
					carDetail.setCarBrand(rs.getString(car_brand));
					carDetail.setCarName(rs.getString(car_name));
					carDetail.setCarId(rs.getInt(car_id));
					carDetail.setDrivenKm(rs.getInt(driven_km));
					carDetail.setPrice(rs.getInt(price));
					carDetail.setFuelType(rs.getString(fuel_type));
					carDetail.setCarAvailableCity(rs.getString(car_available_city));
					carDetail.setRegistrationNo(rs.getString(registration_no));
					carDetail.setTrType(rs.getString(tr_type));
					carDetail.setRegYear(rs.getInt(reg_year));
					ar.add(carDetail);
				}
			}
		} catch (SQLException e) {
			log.error(e);

			throw new DbException("unable to retrive");

		}
		return ar;
	}

	@Override
	public List<CarDetail> getCarDetailUseFuelType(String fuelType) throws DbException {
		List<CarDetail> ar = new ArrayList<CarDetail>();
		String sql = "select car_brand,car_name,reg_year,car_id,driven_km,price,fuel_type,car_available_city,registration_no,tr_type from car_detail where fuel_type=?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, fuelType);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					CarDetail carDetail = new CarDetail();
					carDetail.setCarBrand(rs.getString(car_brand));
					carDetail.setCarName(rs.getString(car_name));
					carDetail.setCarId(rs.getInt(car_id));
					carDetail.setDrivenKm(rs.getInt(driven_km));
					carDetail.setPrice(rs.getInt(price));
					carDetail.setFuelType(rs.getString(fuel_type));
					carDetail.setCarAvailableCity(rs.getString(car_available_city));
					carDetail.setRegistrationNo(rs.getString(registration_no));
					carDetail.setTrType(rs.getString(tr_type));
					carDetail.setRegYear(rs.getInt(reg_year));
					ar.add(carDetail);
				}
			}
		} catch (SQLException e) {
			log.error(e);

			throw new DbException("unable to retrive");

		}
		return ar;
	}

	@Override
	public List<CarDetail> getCarDetailUseCarId(int carId) throws DbException {
		List<CarDetail> ar = new ArrayList<CarDetail>();
				String sql = "select * from  car_detail t left outer join car_seller d on t.car_seller_id=d.seller_id where t.car_id=? and t.status='available'";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, carId);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					CarDetail c = new CarDetail();
					c.setCarId(rs.getInt(car_id));
					c.setCarOwnerId(rs.getInt(seller_id));
					c.setCarName(rs.getString(car_name));
					c.setCarBrand(rs.getString(car_brand));
					c.setTrType(rs.getString(tr_type));
					c.setFuelType(rs.getString(fuel_type));
					c.setRegState(rs.getString(reg_state));
					c.setRegYear(rs.getInt(reg_year));
					c.setDrivenKm(rs.getInt(driven_km));
					c.setPrice(rs.getInt(price));
					c.setStatus(rs.getString(statuss));
					c.setRegistrationNo(rs.getString(registration_no));
					c.setImageSrc(rs.getString(image));
					CarOwner carowner = new CarOwner();
					carowner.setownerName(rs.getString(seller_name));
					carowner.setContactNo(rs.getLong("seller_contact_no"));
					c.setCarOwner(carowner);
					ar.add(c);
				}
			}
		} catch (SQLException e) {
			log.error(e);
			throw new DbException("unable to retrive the car information!!");
		}

		return ar;
	}

	@Override
	public List<CarDetail> viewAllCar() throws DbException {
		List<CarDetail> ar = new ArrayList<CarDetail>();
		String stat="available";
		String sql = "select * from  car_detail where status=?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1,stat);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					CarDetail c = new CarDetail();
					c.setCarId(rs.getInt(car_id));
					c.setCarOwnerId(rs.getInt("car_seller_id"));
					c.setCarName(rs.getString(car_name));
					c.setCarBrand(rs.getString(car_brand));
					c.setTrType(rs.getString(tr_type));
					c.setFuelType(rs.getString(fuel_type));
					c.setRegState(rs.getString(reg_state));
					c.setRegYear(rs.getInt(reg_year));
					c.setDrivenKm(rs.getInt(driven_km));
					c.setPrice(rs.getInt(price));
					c.setStatus(rs.getString(statuss));
					c.setRegistrationNo(rs.getString(registration_no));
					c.setImageSrc(rs.getString(image));
					CarOwner carowner = new CarOwner();
					c.setCarOwner(carowner);
					ar.add(c);
				}
			}
		
	} catch (SQLException e) {
		log.error(e);
		throw new DbException("unable to retrive the car information!!");
	}
		return ar;
	}
	public boolean isCarAlreadyRegistered(String regNo)throws DbException
	{
		boolean exists=false;
		String sqll="select * from car_detail where registration_no=?";
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sqll);) {
			
				ps.setString(1,regNo);
				try (ResultSet rs = ps.executeQuery();) {
					if (rs.next()) {
						exists = true;
					}
				}
			}catch (SQLException e) {
				log.error(e);
			}
			return exists;
			
		
	}
}
	
