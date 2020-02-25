package org.solarsystem.web.dao.repository;

import org.solarsystem.web.dao.DBConnection;
import  org.solarsystem.web.dao.entity.User;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Logger;

public class UserRepository {

    public User getUserByEmailByPassword(String email, String password) {
        DBConnection dbConnection = new DBConnection();
        String sql = "SELECT id, name, email, password FROM solar_system.user_admin "+
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
            e.printStackTrace();
        }
        return null;
    }
}
