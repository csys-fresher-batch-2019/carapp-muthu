package com.chainsys.carsale.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection() {
		Connection connection = null;
		try {
			String server = "localhost";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@" + server + ":1521:XE";
			connection = DriverManager.getConnection(url, "system", "oracle");
			System.out.println(connection);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Driver class not found");
		} catch (SQLException e) {

			throw new RuntimeException("invalid DB credentials" + e.getMessage());
		}
		return connection;
	}

}
