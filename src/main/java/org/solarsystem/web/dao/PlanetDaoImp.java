package org.solarsystem.web.dao;

import org.solarsystem.web.entity.Planet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    }

    @Override
    public Planet getPlanetById(int id) {
        DBConnection dbConnection = new DBConnection();
        String sql = "SELECT * FROM solar_system.planets where id="+id;

        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);
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
                    getPlanetImages(0)
                );
                System.out.println(planet);
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
        list.add("For now, that is temp arrayList");
        list.add("I will finish that method in the near future");

        return list;
    }


}
