package com.chainsys.carsale.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
	
	public static Connection getConnection() throws Exception
	{
		String server="cslh2003";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@"+server+":1521:XE","system","oracle");
		System.out.println(connection);
	return connection;
	}

}
