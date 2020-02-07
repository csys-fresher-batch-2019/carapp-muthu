package com.chainsys.carsale.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection()  {
		Connection connection = null;
		try {
			String server = "cslh2003";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@" + server + ":1521:XE", "system",
					"oracle");
			System.out.println(connection);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Driver class not found");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("invalid DB credentials");
		}
		return connection;
	}

}
