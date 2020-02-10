package org.solarsystem.web.dao;

import org.solarsystem.web.entity.Planet;

import java.sql.*;
import java.util.ArrayList;

public class PlanetDaoImp implements PlanetDao {
    /*In this class will be get and set data from DB*/

    @Override
    public void addPlanet(Planet planet) {

    }

    @Override
    public void updatePlanet(Planet planet) {

    }

    @Override
    public void removePlanet(int id) {
        DBConnection dbConnection = new DBConnection();
        String sql = "DELETE FROM solar_system.planets where id="+id;

        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()
        ){
            statement.executeUpdate(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }


    }

    @Override
    public Planet getPlanetById(int id) {
        DBConnection dbConnection = new DBConnection();
        String sql = "SELECT * FROM solar_system.planets where id="+id;

        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
             ){
            if (resultSet.next()){
                Planet planet = new Planet(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getDate("start_date"),
                    resultSet.getDouble("orbital_period"),
                    resultSet.getDouble("diameter"),
                    resultSet.getDouble("gravity"),
                    resultSet.getBoolean("is_satellites"),
                    resultSet.getString("short_description"),
                    resultSet.getString("full_description"),
                    resultSet.getString("language_id"),
                    getPlanetImages(id)
                );
                return planet;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getPlanetImages(int id) {
        ArrayList<String> list = new ArrayList<>();
        DBConnection dbConnection = new DBConnection();
        String sql = "SELECT * FROM solar_system.images where id_planet="+id;

        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ){
            if (resultSet.next()){
                list.add(resultSet.getString("path_to_the_file"));
                while (resultSet.next()){
                    list.add(resultSet.getString("path_to_the_file"));
                }
                return list;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


}
