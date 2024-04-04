package model;

import database.CRUD;
import database.ConfigDB;
import entity.Airplane;
import entity.Flight;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightModel implements CRUD {

    AirplaneModel objAM = new AirplaneModel();

    @Override
    public Object create(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Flight flight = (Flight) object;

        try {
            String sql = "INSERT INTO flights (destination,departure_date,departure_time,id_airplane) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, flight.getDestination());
            objPrepare.setString(2, flight.getDeparture_date());
            objPrepare.setString(3, flight.getDeparture_time());
            objPrepare.setInt(4, flight.getId_airplane());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                flight.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Success!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, STR."Error  3206: \{e.getMessage()}");
        }

        ConfigDB.closeConnection();
        return flight;
    }

    @Override
    public List<Object> read(Object object) {
        return null;
    }

    @Override

    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Flight objFlight = (Flight) object;
        boolean isUpdated = false;

        try{
            String sql = "UPDATE flight SET name = ?, last_name = ?, id_specialty = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1, objFlight.getName());
            objPrepared.setString(2, objFlight.getLast_name());
            objPrepared.setInt(3, objFlight.getId_specialty());

            objPrepared.setInt(4, objFlight.getId());

            int rowAffected = objPrepared.executeUpdate();

            if(rowAffected>0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Successful update ");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"ERR 5352: "+ e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object object) {
        //!1 Convert object to entity.
        Flight objFlight = (Flight) object;
        //2. Elimination state
        boolean isDeleted = false;
        //3.Open connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Write sentence
            String sql = "DELETE FROM flight WHERE flight.id = ?;";
            //5.
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //6.
            objPrepare.setInt(1, objFlight.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The delete was successful");
            }

            ConfigDB.closeConnection();
            return isDeleted;

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error 4541: " + e.getMessage());
        }

        return isDeleted;
    }

    @Override
    public Object findById(int id) {
        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        Flight objFlight = null;
        try{
            //2. SQL Sentence
            String sql = "SELECT * FROM flight WHERE flight.id = ?";
            //3. Preparing statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //4.
            objPrepare.setInt(1,id);
            //5. execute
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objFlight = new Flight();
                objFlight.setId(objResult.getInt("id"));
                objFlight.setName(objResult.getString("name"));
                objFlight.setLast_name(objResult.getString("last_name"));
                objFlight.setId_specialty(objResult.getInt("id_specialty"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objFlight;
    }

    public List<Object> findAll() {
            /*CREATE TABLE flights(
        id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
        destination varchar(40) NOT NULL,
        departure_date DATE  NOT NULL,
        departure_time TIME NOT NULL,
        id_airplane INT NOT NULL,
        FOREIGN KEY (id_airplane) REFERENCES airplanes(id)
        );*/
        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Initialize a list to save data.
        List<Object> listflight = new ArrayList<>();

        try{
            //3. Write sentence:
            String sql = "SELECT * FROM flight INNER JOIN airplanes ON flight.id_airplane = airplane.id ORDER BY flight.id ASC;";
            //4. Use preparedStatement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5. Execute
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6. Get results
            while(objResult.next()){
                Flight objFlight = new Flight();
                objFlight.setId(objResult.getInt("id"));
                objFlight.setDestination(objResult.getString("destination"));
                objFlight.setDeparture_date(objResult.getString("departure_date"));
                objFlight.setDeparture_time(objResult.getString("departure_time"));
                objFlight.setId_airplane(objResult.getInt("id_airplane"));
                objFlight.setModel_airplane(objResult.getString("airplane.model"));

                listflight.add(objFlight);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }

        //7. Close connection
        ConfigDB.closeConnection();

        return listflight;
    }
}
