package com.chainsys.carsale.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection()  {
		Connection connection = null;
		try {
			String server = "cslh2003";
			String system="system";
			String password="oracle";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@" + server + ":1521:XE", system,password);
			System.out.println(connection);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Driver class not found");
		} catch (SQLException e) {
			
			throw new RuntimeException("invalid DB credentials");
		}
		return connection;
	}

}
