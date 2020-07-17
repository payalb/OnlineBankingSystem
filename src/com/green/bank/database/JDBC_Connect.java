package com.green.bank.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.green.bank.util.DatabaseException;


public class JDBC_Connect {
	
	static Logger logger= Logger.getLogger(JDBC_Connect.class);
	/*
	 * public static Connection getConnection() throws SQLException, IOException {
	 * Properties p = new Properties();
	 * p.load(JDBC_Connect.class.getResourceAsStream("/database.properties"));
	 * String url = p.getProperty("jdbc.url"); String username =
	 * p.getProperty("jdbc.username"); String password =
	 * p.getProperty("jdbc.password"); Connection connection =
	 * DriverManager.getConnection(url, username, password); return connection;
	 * 
	 * }
	 */
	static BasicDataSource  ds= new BasicDataSource();
	static {
		Properties p = new Properties();
		try {
		p.load(JDBC_Connect.class.getResourceAsStream("/database.properties"));
		ds.setUsername(p.getProperty("jdbc.username"));
		ds.setPassword(p.getProperty("jdbc.password"));
		ds.setUrl( p.getProperty("jdbc.url"));
		ds.setMaxTotal(200);
		ds.setMinIdle(50);
		ds.setMaxConnLifetimeMillis(2000);//after 2 sec, connection would end automatically
		ds.setMaxWaitMillis(1000);//200 requests, already got the connection
		//201 is coming, so withn 2 sec, possibility that the connection would be free
		}catch(IOException e) {
			logger.error("Unable to read the property file database.properties"+ e.getMessage());
		}
	}
	//connection pool
	public static Connection getConnection() throws DatabaseException{
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			throw new DatabaseException("Error obtaining connection: "+ e.getMessage());
		}
	}
	
	
}
