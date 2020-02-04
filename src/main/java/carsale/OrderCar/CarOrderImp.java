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

	public void orderCar(CarOrder carorder) throws Exception {
		// TODO Auto-generated method stub
		Connection con=TestConnection.getConnection();
		String check=" select car_id as cdcarid from car_detail where car_id = " + carorder.getCarid();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(check);
		int carId = 0;
		
		if (rs.next()) {
			try {
			String sql="insert into car_order(order_id,buyer_name,buyer_contact_number,car_id,seller_id,test_drive,)values(order_id_sq.nextval,?,?,?,?,?)";
			Statement stt=con.createStatement();
			PreparedStatement pst = con.prepareStatement(sql);
			// int row=pst.executeUpdate(sql);
			pst.setString(1,carorder.getBuyerName());
			pst.setLong(2, carorder.getBuyerContactNo());
			pst.setInt(3, carorder.getCarid());
			pst.setInt(4,carorder.getSellerId());
			pst.setString(5, carorder.getTestDrive());
			int rows=pst.executeUpdate();
			System.out.println(rows);
			System.out.println(sql);
			
			
			String upsql="update car_detail  set status='not available' where car_id="+ carorder.getCarid();
			Statement upst=con.createStatement();
			int uprs=upst.executeUpdate(upsql);
		}
			catch(Exception e)
			{
				
				System.out.println(e.getMessage());
			}
		}
		}
		


	public ArrayList<CarOrder> getCarDeleveryDate(int orderId) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<CarOrder> lt=new ArrayList<CarOrder>();
		Connection con=TestConnection.getConnection();
		String sql="select buyer_name ,order_id,car_id,delivered_date from car_order where order_id=?";
		
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setInt(1,orderId);
	   ResultSet rs=pst.executeQuery();
      
	   while(rs.next())
	   {
		   CarOrder c=new CarOrder();
		   c.setBuyerName(rs.getString("buyer_name"));
		   c.setOrderId(rs.getInt("order_id"));
		   c.setCarid(rs.getInt("car_id"));
		   c.setDeliveredDate(rs.getDate("delivered_date"));
		   lt.add(c);
	   }
	 	   return lt;
	}
		
	public ArrayList<CarOrder> getDeliveryCarDet(String deliver) throws Exception {
		// TODO Auto-generated method stub
		Connection con=TestConnection.getConnection();
		ArrayList<CarOrder> ts=new ArrayList<CarOrder>();
		LocalDate ld=LocalDate.parse(deliver);
		Date da=Date.valueOf(ld);
		String sql="select c.car_name ,d.delivered_date,d.buyer_name from car_order d,car_detail c where trunc(d.delivered_date)=? and c.car_id=d.car_id"; 
		PreparedStatement pst=con.prepareStatement(sql);
		pst.setDate(1,da);
		//pst.setInt(2,orderid );
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
		con.close();
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
