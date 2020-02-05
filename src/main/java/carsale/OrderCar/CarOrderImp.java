package carsale.OrderCar;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import carsale.TestConnection;

public class CarOrderImp implements CarOrderDAO{

	public void orderCar(CarOrder carOrder) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement ps=null;
		PreparedStatement pst=null;
		Statement stt=null;
		try {
			con=TestConnection.getConnection();
		 String check=" select car_id  from car_detail where car_id = ?" ;
		 ps=con.prepareStatement(check);
		ps.setInt(1,carOrder.getCarId());
		ResultSet rs=ps.executeQuery();
		
		if (rs.next()) {
		
				int carId=rs.getInt("car_id");
			String sql="insert into car_order(order_id,buyer_name,buyer_contact_number,car_id,seller_id,test_drive,address1,address2,city,buyer_state,pincode)values(order_id_sq.nextval,?,?,?,?,?,?,?,?,?,?)";
			System.out.println(sql);
			stt=con.createStatement();
			pst = con.prepareStatement(sql);
			// int row=pst.executeUpdate(sql);
			pst.setString(1,carOrder.getBuyerName());
			pst.setLong(2, carOrder.getBuyerContactNo());
			pst.setInt(3, carId);
			pst.setInt(4,carOrder.getSellerId());
			pst.setString(5, carOrder.getTestDrive());
			pst.setString(6,carOrder.getAddress1());
			pst.setString(7, carOrder.getAddress2());
			pst.setString(8,carOrder.getCity());
			pst.setString(9,carOrder.getBuyerState());
			pst.setInt(10, carOrder.getPincode());
			int rows=pst.executeUpdate();
			System.out.println(rows);
			System.out.println(sql);
			
			
			String upsql="update car_detail  set status='not available' where car_id=?";
	       PreparedStatement pt=con.prepareStatement(upsql);
	       pt.setInt(1,  carOrder.getCarId());
			//Statement upst=con.createStatement();
			int uprs=pt.executeUpdate();
		}
			
		
		}catch(Exception e)
		{
			if(stt!=null) {
				stt.close();
			}
			if(pst!=null)
			{
				pst.close();
			}
			if(ps!=null)
			{
				ps.close();
			}
			if(con!=null)
			{
				con.close();
			}
		}
		}
		


	public ArrayList<CarOrder> getCarDeleveryDate(int orderId) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<CarOrder> lt=new ArrayList<CarOrder>();
		Connection con=null;
		PreparedStatement pst=null;
		try {
			con=TestConnection.getConnection();
		String sql="select buyer_name ,order_id,car_id,delivered_date from car_order where order_id=?";
		 pst=con.prepareStatement(sql);
		pst.setInt(1,orderId);
	   ResultSet rs=pst.executeQuery();
      
	   while(rs.next())
	   {
		   CarOrder c=new CarOrder();
		   c.setBuyerName(rs.getString("buyer_name"));
		   c.setOrderId(rs.getInt("order_id"));
		   c.setCarId(rs.getInt("car_id"));
		   c.setDeliveredDate(rs.getDate("delivered_date"));
		   lt.add(c);
	   }
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pst!=null)
			{
				pst.close();
			}
			if(con!=null)
			{
				con.close();
			}
		}
	 	   return lt;
	}
		
	public ArrayList<CarOrder> getDeliveryCarDet(int orderId) throws Exception {
		// TODO Auto-generated method stub
	Connection con =null;
	PreparedStatement pst=null;
	ArrayList<CarOrder> ts=new ArrayList<CarOrder>();
		try {
		 con=TestConnection.getConnection();
		
		
		//LocalDate ld=LocalDate.parse(deliver);
		//Date da=Date.valueOf(ld);
		String sql="select c.car_name ,d.delivered_date,d.buyer_name from car_order d,car_detail c where order_id=?  and c.car_id=d.car_id "; 
		 pst=con.prepareStatement(sql);
		//pst.setDate(1,da);
		pst.setInt(1,orderId);
		
		ResultSet rs=pst.executeQuery();
				
		while(rs.next())
		{
			CarOrder cc=new CarOrder();
		   cc.setCarName(rs.getString("car_name"));
		  cc.setDeliveredDate(rs.getDate("delivered_date"));
		  cc.setBuyerName(rs.getString("buyer_name"));
		ts.add(cc);
		}
		System.out.println(sql);
		}catch(Exception e)
		{
		e.printStackTrace();
		}
		finally
		{
			if(pst!=null)
			{
				pst.close();
			}
			if(con!=null)
			{
				con.close();
			}
		}
		return ts;
	}

	/*public void updateCarStatus(int carId) throws Exception {
		// TODO Auto-generated method stub
		CarDetail c=new CarDetail();
		Connection con=TestConnection.getConnection();
		String sql="update car_detail  set status='not available' where car_id=(select car_id from car_order where car_order.car_id=cardetail.?";
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setInt(1,c.carId);
		int row=pst.executeUpdate();
		System.out.println(row);
	}*/

}
