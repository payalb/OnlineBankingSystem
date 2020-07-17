/*
 * package com.green.bank.service;
 * 
 * import javax.servlet.ServletContextEvent; import
 * javax.servlet.ServletContextListener; import
 * javax.servlet.annotation.WebListener;
 * 
 * import org.flywaydb.core.Flyway;
 * 
 * @WebListener public class FlywayMigration implements ServletContextListener {
 * 
 * @Override public void contextInitialized(ServletContextEvent sce) { Flyway
 * flyway = new Flyway(); flyway.setBaselineOnMigrate(true);// 1.0 used for
 * flyway flyway.setLocations("classpath:/databasefiles"); flyway.setDataSource(
 * "jdbc:oracle:thin:@oracledb.cm9c2khogcy8.ap-south-1.rds.amazonaws.com:1521:ORACLEDB",
 * "oracledb", "oracledb"); flyway.migrate();
 * 
 * } } // connection pool
 */