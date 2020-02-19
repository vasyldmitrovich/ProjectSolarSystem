package org.solarsystem.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements AutoCloseable {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost:3306/";
    /*Change
    name data base,
    user and
    password for your DB.*/
    static final String nameDB = "solar_system";
    static final String user = "root";
    static final String password = "rootStrong&serverTimezone=UTC";
    private Connection connection = null;

    public DBConnection(){
        try {
            Class.forName(JDBC_DRIVER).newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e){
            e.printStackTrace();
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
