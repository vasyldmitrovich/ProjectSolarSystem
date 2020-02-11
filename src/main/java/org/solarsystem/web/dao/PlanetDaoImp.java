package org.solarsystem.web.dao;

import org.solarsystem.web.entity.Planet;

import java.sql.*;
import java.util.ArrayList;

public class PlanetDaoImp implements PlanetDao {
    /*This class implement interface. Get and set data from DB*/

    @Override
    public void addPlanet(Planet planet) {
        int isSatellites = planet.isSatellites() ? 1:0;
        DBConnection dbConnection = new DBConnection();
        String sql = "INSERT INTO solar_system.planets (name, orbital_period, diameter, " +
                "gravity, is_satellites, short_description, full_description, language_id)" +
                "VALUES ('"+planet.getName()+"',"+planet.getOrbitalPeriod()+","+
                planet.getDiameter()+","+planet.getGravity()+","+isSatellites+",'"+
                planet.getShortDescription()+"','"+planet.getFullDescription()+"','"+planet.getLanguageId()+"');";
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void updatePlanet(Planet planet) {
        int isSatellites = planet.isSatellites() ? 1:0;
        DBConnection dbConnection = new DBConnection();
        String sql = "UPDATE solar_system.planets SET name='"
                +planet.getName()+"', orbital_period='"+planet.getOrbitalPeriod()
                +"', diameter='"+planet.getDiameter()
                +"', gravity='"+planet.getGravity()
                +"', is_satellites='"+isSatellites
                +"', short_description='"+planet.getShortDescription()
                +"', full_description='"+planet.getFullDescription()
                +"', language_id='"+planet.getLanguageId()+"' where id='"+planet.getId()+"';";
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);

        } catch (SQLException e){
            e.printStackTrace();
        }
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

    @Override
    public void addImageByIdPlanet(String pathToTheFile, int idPlanet) {
        DBConnection dbConnection = new DBConnection();
        String sql = "INSERT INTO solar_system.images (path_to_the_file, id_planet)" +
                "VALUES ('"+pathToTheFile+"',"+idPlanet+");";
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }


}
