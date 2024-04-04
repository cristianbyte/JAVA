package model;

import database.CRUD;
import database.ConfigDB;
import entity.Airplane;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirplaneModel implements CRUD {
    @Override
    public Object create(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Airplane airplane = (Airplane) object;

        try {
            String sql = "INSERT INTO airplanes (model,capacity) VALUES (?,?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, airplane.getModel());
            objPrepare.setInt(2, airplane.getCapacity());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                airplane.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Success!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error  3526: " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return airplane;
    }

    @Override
    public List<Object> read(Object object) {
        return null;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        //!1 Convert object to entity.
        Airplane objAirplane = (Airplane) object;
        //2. Elimination state
        boolean isDeleted = false;
        //3.Open connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Write sentence
            String sql = "DELETE FROM airplanes WHERE airplanes.id = ?;";
            //5.
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //6.
            objPrepare.setInt(1, objAirplane.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The delete was successful");
            }

            ConfigDB.closeConnection();
            return isDeleted;

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error 0521: " + e.getMessage());
        }

        return isDeleted;
    }

    @Override
    public Object findById(int id) {
        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        Airplane objAirplane = null;
        try{
            //2. SQL Sentence
            String sql = "SELECT * FROM airplanes WHERE airplanes.id = ?";
            //3. Preparing statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //4.
            objPrepare.setInt(1,id);
            //5. execute
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objAirplane = new Airplane();
                objAirplane.setId(objResult.getInt("id"));
                objAirplane.setModel(objResult.getString("model"));
                objAirplane.setCapacity(objResult.getInt("capacity"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objAirplane;
    }
    /*CREATE TABLE airplanes(
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
model varchar(40) NOT NULL,
capacity INT NOT NULL
);
*/
    public List<Object> findAll() {

        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Initialize a list to save data.
        List<Object> listSpecialties = new ArrayList<>();

        try{
            //3. Write sentence:
            String sql = "SELECT * FROM airplanes ORDER BY airplanes.id ASC;";
            //4. Use preparedStatement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5. Execute
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6. Get results
            while(objResult.next()){
                Airplane objAirplane = new Airplane();
                objAirplane.setId(objResult.getInt("id"));
                objAirplane.setModel(objResult.getString("model"));
                objAirplane.setCapacity(objResult.getInt("capacity"));

                listSpecialties.add(objAirplane);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }

        //7. Close connection
        ConfigDB.closeConnection();

        return listSpecialties;
    }
}





