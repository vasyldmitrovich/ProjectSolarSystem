package org.solarsystem.web.dao.repository;

import org.apache.log4j.Logger;
import org.solarsystem.web.dao.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
/*Add feedback to DB*/
public class FeedbackRepository {

    public static final Logger log = Logger.getLogger(UserRepository.class);

    public static void addFeedback(String First_Name, String Last_Name, String Email,
                                   String Subject, String Comments) {
        log.info("Start add feedback to DB");
        Connection connection = null;
        Statement stmt = null;
        DBConnection dataSource = new DBConnection();
        try {
            connection = dataSource.getConnection();
            stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO feedback (First_Name, Last_Name, Email, Subject, Comments)" +
                    "VALUES ('" + First_Name + "','" + Last_Name + "', '" + Email + "','" + Subject + "','" +
                    Comments + "')");
        } catch (SQLException e) {
            log.info("Could not added feedback to DB" + e);
        } finally {
            try {
                if (connection != null) connection.close();
                if (stmt != null) stmt.close();
            } catch (SQLException exc) {
                log.info("Could not close connection" + exc);
            }
        }
    }
}

