package model;

import database.CRUD;
import database.ConfigDB;
import entity.Specialty;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialtyModel implements CRUD {
    @Override
    public Object create(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Specialty specialty = (Specialty) object;

        try {

            String sql = "INSERT INTO specialties (name,description) VALUES (?,?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, specialty.getName());
            objPrepare.setString(2, specialty.getDescription());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                specialty.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Success!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error  3526: " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return  specialty;
    }

    @Override
    public List<Object> read(Object object) {
        return null;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Specialty objSpecialty = (Specialty) object;
        boolean isUpdated = false;

        try{
            String sql = "UPDATE specialties SET name = ?, description = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1,objSpecialty.getName());
            objPrepared.setString(2,objSpecialty.getDescription());

            objPrepared.setInt(3,objSpecialty.getId());

            int rowAffected = objPrepared.executeUpdate();

            if(rowAffected>0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Successful update ");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"ERR 1352: "+ e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object object) {
        //!1 Convert object to entity.
        Specialty objSpecialty = (Specialty) object;
        //2. Elimination state
        boolean isDeleted = false;
        //3.Open connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Write sentence
            String sql = "DELETE FROM specialties WHERE specialties.id = ?;";
            //5.
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //6.
            objPrepare.setInt(1, objSpecialty.getId());

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
        Specialty objSpecialty = null;
        try{
            //2. SQL Sentence
            String sql = "SELECT * FROM specialties WHERE specialties.id = ?";
            //3. Preparing statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //4.
            objPrepare.setInt(1,id);
            //5. execute
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objSpecialty = new Specialty();
                objSpecialty.setId(objResult.getInt("id"));
                objSpecialty.setName(objResult.getString("name"));
                objSpecialty.setDescription(objResult.getString("description"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objSpecialty;
    }

    public List<Object> findAll() {

        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Initialize a list to save data.
        List<Object> listSpecialties = new ArrayList<>();

        try{
            //3. Write sentence:
            String sql = "SELECT * FROM specialties ORDER BY specialties.id ASC;";
            //4. Use preparedStatement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5. Execute
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6. Get results
            while(objResult.next()){
                Specialty objSpecialty = new Specialty();
                objSpecialty.setId(objResult.getInt("id"));
                objSpecialty.setName(objResult.getString("name"));
                objSpecialty.setDescription(objResult.getString("description"));

                listSpecialties.add(objSpecialty);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }

        //7. Close connection
        ConfigDB.closeConnection();

        return listSpecialties;
    }
}





