package org.solarsystem.web.dao.repository;

import org.solarsystem.web.dao.DBConnection;
import org.solarsystem.web.dao.PlanetDao;
import org.solarsystem.web.dao.entity.Planet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "SELECT a.*, GROUP_CONCAT(DISTINCT b.path_to_the_file ORDER BY b.path_to_the_file ASC SEPARATOR ', ') AS array_images\n" +
                "    FROM `planets` a \n" +
                "    LEFT JOIN `images` b ON a.id=b.id_planet\n" +
                "    WHERE a.id="+id;

        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
             ){
            if (resultSet.next()){
                String string = resultSet.getString("array_images");
                System.out.println(string);
                ArrayList<String> listImages = new ArrayList<>();
                String [] str_array = string.split(", ");
                for (int i=0;i<str_array.length;i++){
                    listImages.add(str_array[i]);
                }
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
                    listImages
                );
                return planet;
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

    @Override
    public List<Planet> getAllPlanets() {
        List<Planet> planets = new ArrayList<>();
        DBConnection dbConnection = new DBConnection();
        String sql = "SELECT a.*, GROUP_CONCAT(DISTINCT b.path_to_the_file ORDER BY b.path_to_the_file ASC SEPARATOR ', ') AS array_images\n" +
                "    FROM `planets` a\n" +
                "    LEFT JOIN `images` b ON a.id=b.id_planet\n" +
                "    GROUP BY a.id;";

        try (Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)
        ){
             while (resultSet.next()){
                 String string = resultSet.getString("array_images");
                 System.out.println(string);
                 ArrayList<String> listImages = new ArrayList<>();
                 String [] str_array = string.split(", ");
                 for (int i=0;i<str_array.length;i++){
                     listImages.add(str_array[i]);
                 }

                planets.add(new Planet(resultSet.getLong("id"),resultSet.getString("name"),
                        resultSet.getDouble("orbital_period"),resultSet.getDouble("diameter"),
                        resultSet.getDouble("gravity"),resultSet.getBoolean("is_satellites"),
                        resultSet.getString("short_description"),resultSet.getString("full_description"),
                        resultSet.getString("language_id"),listImages));
            }
            return planets;
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Planet getPlanetByName(String name) {
        DBConnection dbConnection = new DBConnection();
        String sql = "SELECT a.*, GROUP_CONCAT(DISTINCT b.path_to_the_file ORDER BY b.path_to_the_file ASC SEPARATOR ', ') AS array_images\n" +
                "    FROM `planets` a \n" +
                "    LEFT JOIN `images` b ON a.id=b.id_planet\n" +
                "    WHERE a.name="+name;

        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ){
            if (resultSet.next()){
                String string = resultSet.getString("array_images");
                System.out.println(string);
                ArrayList<String> listImages = new ArrayList<>();
                String [] str_array = string.split(", ");
                for (int i=0;i<str_array.length;i++){
                    listImages.add(str_array[i]);
                }
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
                        listImages
                );
                return planet;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


}
