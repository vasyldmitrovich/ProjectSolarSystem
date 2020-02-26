package org.solarsystem.web.dao.repository;

import org.apache.log4j.Logger;
import org.solarsystem.web.dao.DBConnection;
import org.solarsystem.web.dao.entity.User;


import java.sql.*;
/*Get user by email and password*/
public class UserRepository {

    public static final Logger log = Logger.getLogger(UserRepository.class);

    public User getUserByEmailByPassword(String email, String password) {
        log.info("Start get user by email by password from DB");
        DBConnection dbConnection = new DBConnection();
        String sql = "SELECT id, name, email, password FROM solar_system.user_admin " +
                "WHERE user_admin.email='" + email + "' AND user_admin.password='" + password + "'";
        try (
                Connection connection = dbConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {
            if (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("name")
                );
                return user;
            }
        } catch (SQLException e) {
            log.info("Could not get user" + e);
        }
        return null;
    }
}
