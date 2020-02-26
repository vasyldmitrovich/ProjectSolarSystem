package org.solarsystem.web.dao;

import org.apache.log4j.Logger;
import org.solarsystem.web.controller.PlanetController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements AutoCloseable {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost:3306/";
    /*Change user and password for your DB.*/
    static final String nameDB = "solar_system";
    static final String user = "root";
    static final String password = "rootStrong&serverTimezone=UTC";
    private Connection connection = null;
    public static final Logger log = Logger.getLogger(DBConnection.class);
    public DBConnection(){
        log.info("Connection is successfully");
        try {
            Class.forName(JDBC_DRIVER).newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e){
            log.info("Connection do not successfully"+e);
            //e.printStackTrace();

        }
    }

    public Connection getConnection(){
        try {
            if (connection == null){
                connection = DriverManager.getConnection(DB_URL+nameDB+"?user="+user+"&password="+password);
            }
        } catch (SQLException e){
            System.out.println("Error: "+e.toString());
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
