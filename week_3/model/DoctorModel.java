package model;

import database.CRUD;
import database.ConfigDB;
import entity.Doctor;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorModel implements CRUD {
    @Override
    public Object create(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Doctor doctor = (Doctor) object;

        try {
            String sql = "INSERT INTO doctors (name,last_name,id_specialty) VALUES (?,?,?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, doctor.getName());
            objPrepare.setString(2, doctor.getLast_name());
            objPrepare.setInt(3, doctor.getId_specialty());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                doctor.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Success!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error  3206: " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return  doctor;
    }

    @Override
    public List<Object> read(Object object) {
        return null;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Doctor objDoctor = (Doctor) object;
        boolean isUpdated = false;

        try{
            String sql = "UPDATE doctors SET name = ?, last_name = ?, id_specialty = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1,objDoctor.getName());
            objPrepared.setString(2,objDoctor.getLast_name());
            objPrepared.setInt(3,objDoctor.getId_specialty());

            objPrepared.setInt(4,objDoctor.getId());

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
        Doctor objDoctor = (Doctor) object;
        //2. Elimination state
        boolean isDeleted = false;
        //3.Open connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Write sentence
            String sql = "DELETE FROM doctors WHERE doctors.id = ?;";
            //5.
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //6.
            objPrepare.setInt(1, objDoctor.getId());

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
        Doctor objDoctor = null;
        try{
            //2. SQL Sentence
            String sql = "SELECT * FROM doctors WHERE doctors.id = ?";
            //3. Preparing statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //4.
            objPrepare.setInt(1,id);
            //5. execute
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objDoctor = new Doctor();
                objDoctor.setId(objResult.getInt("id"));
                objDoctor.setName(objResult.getString("name"));
                objDoctor.setLast_name(objResult.getString("last_name"));
                objDoctor.setId_specialty(objResult.getInt("id_specialty"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objDoctor;
    }

    public List<Object> findAll() {

        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Initialize a list to save data.
        List<Object> listDoctors = new ArrayList<>();

        try{
            //3. Write sentence:
            String sql = "SELECT * FROM doctors ORDER BY doctors.id ASC;";
            //4. Use preparedStatement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5. Execute
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6. Get results
            while(objResult.next()){
                Doctor objDoctor = new Doctor();
                objDoctor.setId(objResult.getInt("id"));
                objDoctor.setName(objResult.getString("name"));
                objDoctor.setLast_name(objResult.getString("last_name"));
                objDoctor.setId_specialty(objResult.getInt("id_specialty"));

                listDoctors.add(objDoctor);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }

        //7. Close connection
        ConfigDB.closeConnection();

        return listDoctors;
    }
}
