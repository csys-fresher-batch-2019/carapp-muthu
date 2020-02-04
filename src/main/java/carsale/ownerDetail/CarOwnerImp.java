package carsale.ownerDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import carsale.TestConnection;
import carsale.carDetail.CarDetail;

public class CarOwnerImp implements CarOwnerDAO {
	

	public boolean isCarOwnerAlreadyRegistered(Long mobileNo) throws Exception {
		boolean exists = false;
		Connection con=TestConnection.getConnection();
		String sqll="select * from car_seller where seller_contact_no="+mobileNo+"";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(sqll);
		if(rs.next())
		{
			exists = true;
		}
		return exists;
	}
	
	public void AddCarOwner(CarOwner carOwner) throws Exception {
		
		    Connection con=TestConnection.getConnection();
		    String sql="insert into car_seller(seller_id,seller_name,seller_contact_no,user_password,address1,address2,city,seller_state,pincode)values(seller_id_sq.nextval,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, carOwner.getownerName());
			pst.setLong(2, carOwner.getContactNo());
			pst.setString(3,carOwner.getPassword());
			pst.setString(4,carOwner.getAddress1());
			pst.setString(5,carOwner.getAddress2());
			pst.setString(6, carOwner.getCity());
			pst.setString(7, carOwner.getState());
			pst.setInt(8,carOwner.getPincode());
			
			 int row=pst.executeUpdate();
			System.out.println(row);
			 System.out.println(sql);
		   }
	  	

	public void deletecarDetail(int carOwnerId, int carId) throws Exception {
		// TODO Auto-generated method stub
		Connection con=TestConnection.getConnection();
	    String sqll="select seller_id from car_seller where seller_id="+carOwnerId+"";
	    Statement st=con.createStatement();
	    ResultSet rs=st.executeQuery(sqll);
	    if(rs.next())
	    {
         int carSellerId=rs.getInt("seller_id");    
	    String sql="delete from  car_detail where car_id=? and car_seller_id=? and lower(status)='available'";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setInt(1,carId);
		pst.setInt(2,carSellerId);
		int rows=pst.executeUpdate();	
		System.out.println(rows);
	}
	     

	}

	public void viewYourCars(int car) throws Exception {
		
	}

	public ArrayList<CarOwner> ViewYourCAr(long mobileno) throws Exception {
		ArrayList<CarOwner> al=new ArrayList<CarOwner>();
		Connection con=TestConnection.getConnection();
		String sql="select * from car_seller s,car_detail c where s.seller_contact_no="+mobileno+"and s.seller_id=c.car_seller_id";
		//String sql="select seller_id from car_seller where seller_contact_no="+mobileno+"";
	    Statement st=con.createStatement();
	    ResultSet rs=st.executeQuery(sql);
		try
	    {
		 while(rs.next())
	    {
	    	CarOwner c=new CarOwner();
	    	CarDetail cd=new CarDetail();
	    c.setownerName(rs.getString("seller_name"));
	    c.setownerId(rs.getInt("seller_id"));
	    cd.setCarAvailableCity(rs.getString("car_available_city"));
	    cd.setCarBrand(rs.getString("car_brand"));
	    cd.setCarName(rs.getString("car_name"));
	    cd.setDrivenKm(rs.getFloat("Driven_km"));
	    cd.setPrice(rs.getFloat("price"));
	    cd.setRegistrationNo(rs.getString("registration_no"));
	    cd.setRegYear(rs.getInt("reg_year"));
	    cd.setVehicleIdNo(rs.getString("vehicle_identification_no"));
	    c.setCardetail(cd);
	    al.add(c);
	    } 	
	  		  
	}catch(Exception e)
	    {
    	System.out.println(e.getMessage());
    }
		
		return al;
	}

	public void updateCarPrice(CarOwner carOwner) throws Exception {
		Connection con=TestConnection.getConnection();
		
		CarDetail cardetail = carOwner.getCardetail();

     	Statement st=con.createStatement();
		String sql= null;
		if ( carOwner.getownerId() != 0) {
			//sql = "update car_detail cd set cd.price="+cardetail.getPrice()+"where car_id = "+cardetail.getCarId()+" and ( car_seller_id = "+carOwner.getownerId()+" or car_seller_id in  ( select seller_id from car_seller where seller_contact_no ="+carOwner.getcontactNo()+"";
			sql="update car_detail cd set cd.price="+cardetail.getPrice()+" where car_id ="+cardetail.getCarId()+" and car_seller_id="+carOwner.getownerId()+""; 
		}
		else if ( carOwner.getContactNo() != 0){
			sql = "update car_detail cd set cd.price="+cardetail.getPrice()+" where car_id ="+cardetail.getCarId()+" and car_seller_id in ( select seller_id from car_seller where seller_contact_no ="+carOwner.getContactNo()+")";
			}
		
     	int row=st.executeUpdate(sql);
     	if(row==1)
     	{
      	System.out.println(row+"Updated successFully");
	}
     	else
     	{
     		System.out.println("Failed to Upadate");
     	}
}
}


