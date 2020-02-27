package org.solarsystem.web.dao;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*Connection to DB*/
public class DBConnection implements AutoCloseable {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost:3306/";
    /*Change strings user and password for your DB.*/
    static final String nameDB = "solar_system";
    static final String user = "root";
    static final String password = "rootStrong&serverTimezone=UTC";

    private Connection connection = null;
    public static final Logger log = Logger.getLogger(DBConnection.class);

    public DBConnection(){
        try {
            Class.forName(JDBC_DRIVER).newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e){
            log.info("Connection to DB do not successfully"+e);
        }
    }

    public Connection getConnection(){
        log.info("get connection to DB");
        try {
            if (connection == null){
                connection = DriverManager.getConnection(DB_URL+nameDB+"?user="+user+"&password="+password);
            }
        } catch (SQLException e){
            log.info("Could not get connection to DB "+e);
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        log.info("close connection");
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            log.info("Do not close connection: "+e);
        }
    }

}
