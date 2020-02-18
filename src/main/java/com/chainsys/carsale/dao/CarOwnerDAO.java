package com.chainsys.carsale.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.chainsys.carsale.model.CarOrder;
import com.chainsys.carsale.model.CarOwner;
import com.chainsys.carsale.util.DbException;

public interface CarOwnerDAO {
   //@SqlUpdate("insert into car_seller(seller_id,seller_name,seller_contact_no,user_password,address1,address2,city,seller_state,pincode)values(seller_id_sq.nextval,?,?,?,?,?,?,?,?)")
	void addCarOwner(@BindBean CarOwner carOwner) throws DbException;
  // @SqlQuery("select seller_id from car_seller where seller_id=?")
	void deleteCarDetail(int carOwnerId, int carId) throws DbException;
   //@SqlQuery("select * from car_seller s,car_detail c where (s.seller_contact_no=? or s.seller_Id=?) and s.seller_id=c.car_seller_id")
   public List<CarOwner> viewYourCar(long mobileNo) throws DbException;
  //@SqlUpdate("update car_detail cd set cd.price=? where car_id =? and car_seller_id in ( select seller_id from car_seller where seller_contact_no =?)")
	void updateCarPrice(CarOwner carOwner) throws DbException;
  // @SqlQuery("select * from car_seller where seller_contact_no=?")
   public boolean isCarOwnerAlreadyRegistered(Long mobileNo) throws DbException;
   //@SqlQuery("select * from car_order where seller_id=(select seller_id from car_seller where seller_contact_no=?")
	public List<CarOrder> viewYourPlacedCar(Long mobileNo) throws DbException;
}
