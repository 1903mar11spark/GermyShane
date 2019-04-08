package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
//	// do not ever!!!! hardcode credentials like this
//		public static Connection getConnection() throws SQLException {
//			
//			return DriverManager.getConnection("", "", "");
//		}
		
		public static Connection getConnectionFromFile(String filename) throws SQLException, IOException {
			Properties prop = new Properties();
			InputStream in = new FileInputStream(filename);
			prop.load(in);
			return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
	}
//	private static Connection connection;
//	public static Connection getConnection() throws IOException, SQLException {
//		Properties prop = new Properties();
//		
//		InputStream in = new FileInputStream("C:/gitrepos/Bank/project_zero/src/main/java/resources/Connection.prop");
//		
//		prop.load(in);
//		String url = prop.getProperty("url");
//		String username = prop.getProperty("username");
//		String password = prop.getProperty("password");
//		if (connection == null || connection.isClosed()) {
//			connection = DriverManager.getConnection(url, username, password);
//		}
//		return connection;
//	}

		
}
