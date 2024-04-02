package model;

import database.CRUD;
import database.ConfigDB;
import entity.Patient;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientModel implements CRUD {

    public List<Object> findAll() {

        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Initialize a list to save data.
        List<Object> listPatients = new ArrayList<>();

        try{
            //3. Write sentence:
            String sql = "SELECT * FROM patients ORDER BY patients.id ASC;";
            //4. Use preparedStatement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5. Execute
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6. Get results
            while(objResult.next()){
                Patient objPatient = new Patient();
                objPatient.setId(objResult.getInt("id"));
                objPatient.setName(objResult.getString("name"));
                objPatient.setLast_name(objResult.getString("last_name"));
                objPatient.setBirth_date(objResult.getString("birth_date"));
                objPatient.setIdentity(objResult.getString("identity"));

                listPatients.add(objPatient);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }

        //7. Close connection
        ConfigDB.closeConnection();

        return listPatients;
    }

    @Override
    public Object create(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Patient patient = (Patient) object;



        try {
            String sql = "INSERT INTO patients (name,last_name,birth_date,identity) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, patient.getName());
            objPrepare.setString(2, patient.getLast_name());
            objPrepare.setString(3, patient.getBirth_date());
            objPrepare.setString(4, patient.getIdentity());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                patient.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Success!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error  3546: " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return  patient;
    }

    @Override
    public List<Object> read(Object object) {
        return null;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Patient objPatient = (Patient) object;
        boolean isUpdated = false;
            /*CREATE TABLE patients(
        id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
        name varchar(40) NOT NULL,
        last_name varchar(40) NOT NULL,
        birth_date DATE NOT NULL,
        identity varchar(40) NOT NULL
        );*/
        try{
            String sql = "UPDATE patients SET name = ?, last_name = ?, birth_date = ?, identity = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1,objPatient.getName());
            objPrepared.setString(2,objPatient.getLast_name());
            objPrepared.setString(3,objPatient.getBirth_date());
            objPrepared.setString(4,objPatient.getIdentity());
            objPrepared.setInt(5,objPatient.getId());

            int rowAffected = objPrepared.executeUpdate();

            if(rowAffected>0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Successful update ");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"ERR 2352: "+ e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public Object findById(int id) {
        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        Patient objPatient = null;
        try{
            //2. SQL Sentence
            String sql = "SELECT * FROM patients WHERE patients.id = ?";
            //3. Preparing statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //4.
            objPrepare.setInt(1,id);
            //5. execute
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objPatient = new Patient();
                objPatient.setId(objResult.getInt("id"));
                objPatient.setName(objResult.getString("name"));
                objPatient.setLast_name(objResult.getString("last_name"));
                objPatient.setBirth_date(objResult.getString("birth_date"));
                objPatient.setIdentity(objResult.getString("identity"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objPatient;
    }

    @Override
    public boolean delete(Object object) {
        //!1 Convert object to entity.
        Patient objPatient = (Patient) object;
        //2. Elimination state
        boolean isDeleted = false;
        //3.Open connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Write sentence
            String sql = "DELETE FROM patients WHERE patients.id = ?;";
            //5.
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //6.
            objPrepare.setInt(1, objPatient.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The delete was successful");
            }

            ConfigDB.closeConnection();
            return isDeleted;

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error 2528: " + e.getMessage());
        }

        return isDeleted;
    }
}
