package model;

import database.CRUD;
import database.ConfigDB;
import entity.Passenger;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerModel implements CRUD {

    public List<Object> findAll() {

        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Initialize a list to save data.
        List<Object> listPassengers = new ArrayList<>();

        try{
            //3. Write sentence:
            String sql = "SELECT * FROM passengers ORDER BY passengers.id ASC;";
            //4. Use preparedStatement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5. Execute
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6. Get results
            while(objResult.next()){
                Passenger objPassenger = new Passenger();
                objPassenger.setId(objResult.getInt("id"));
                objPassenger.setName(objResult.getString("name"));
                objPassenger.setLast_name(objResult.getString("last_name"));
                objPassenger.setIdentity(objResult.getString("identity"));

                listPassengers.add(objPassenger);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }

        //7. Close connection
        ConfigDB.closeConnection();

        return listPassengers;
    }

    @Override
    public Object create(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Passenger passenger = (Passenger) object;

        try {
            String sql = "INSERT INTO passengers (name,last_name,identity) VALUES (?,?,?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, passenger.getName());
            objPrepare.setString(2, passenger.getLast_name());
            objPrepare.setString(3, passenger.getIdentity());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                passenger.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Success!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error  3546: "+e.getMessage());
        }

        ConfigDB.closeConnection();
        return  passenger;
    }

    @Override
    public List<Object> read(Object object) {
        return null;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Passenger objPassenger = (Passenger) object;
        boolean isUpdated = false;

        try{
            String sql = "UPDATE passengers SET name = ?, last_name = ?, identity = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1,objPassenger.getName());
            objPrepared.setString(2,objPassenger.getLast_name());
            objPrepared.setString(3,objPassenger.getIdentity());
            objPrepared.setInt(4,objPassenger.getId());

            int rowAffected = objPrepared.executeUpdate();

            if(rowAffected>0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Successful update ");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERR 2352: "+e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }
    /*CREATE TABLE passengers(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name varchar(40) NOT NULL,
    last_name varchar(40) NOT NULL,
    identity varchar (40) NOT NULL
    );*/
    @Override
    public Object findById(int id) {
        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        Passenger objPassenger = null;
        try{
            //2. SQL Sentence
            String sql = "SELECT * FROM passengers WHERE passengers.id = ?";
            //3. Preparing statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //4.
            objPrepare.setInt(1,id);
            //5. execute
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objPassenger = new Passenger();
                objPassenger.setId(objResult.getInt("id"));
                objPassenger.setName(objResult.getString("name"));
                objPassenger.setLast_name(objResult.getString("last_name"));
                objPassenger.setIdentity(objResult.getString("identity"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objPassenger;
    }

    @Override
    public boolean delete(Object object) {
        //!1 Convert object to entity.
        Passenger objPassenger = (Passenger) object;
        //2. Elimination state
        boolean isDeleted = false;
        //3.Open connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Write sentence
            String sql = "DELETE FROM passengers WHERE passengers.id = ?;";
            //5.
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //6.
            objPrepare.setInt(1, objPassenger.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The delete was successful");
            }

            ConfigDB.closeConnection();
            return isDeleted;

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error 2528: "+e.getMessage());
        }

        return isDeleted;
    }
}
