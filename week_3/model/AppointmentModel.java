package model;

import database.CRUD;
import database.ConfigDB;
import entity.Appointment;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentModel implements CRUD {
    @Override
    public Object create(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Appointment appointment = (Appointment) object;



        try {
            String sql = "INSERT INTO appointments (date_appointment,time_appointment,reason,id_patient,id_doctor) VALUES (?,?,?,?,?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, appointment.getDate_appointment());
            objPrepare.setString(2, appointment.getTime_appointment());
            objPrepare.setString(3, appointment.getReason());
            objPrepare.setInt(4, appointment.getId_patient());
            objPrepare.setInt(5, appointment.getId_doctor());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                appointment.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Success!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error  6626: " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return  appointment;
    }

    @Override
    public List<Object> read(Object object) {
        return null;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Appointment objAppointment = (Appointment) object;
        boolean isUpdated = false;

        try{
            String sql = "UPDATE appointments SET date_appointment = ? , time_appointment = ? , reason = ? , id_patient = ? , id_doctor = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1,objAppointment.getDate_appointment());
            objPrepared.setString(2,objAppointment.getTime_appointment());
            objPrepared.setString(3,objAppointment.getReason());
            objPrepared.setInt(4,objAppointment.getId_patient());
            objPrepared.setInt(5,objAppointment.getId_doctor());

            objPrepared.setInt(6,objAppointment.getId());

            int rowAffected = objPrepared.executeUpdate();

            if(rowAffected>0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Successful update ");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"ERR 1750: "+ e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object object) {
        //!1 Convert object to entity.
        Appointment objAppointment = (Appointment) object;
        //2. Elimination state
        boolean isDeleted = false;
        //3.Open connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Write sentence
            String sql = "DELETE FROM appointments WHERE appointments.id = ?;";
            //5.
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //6.
            objPrepare.setInt(1, objAppointment.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The delete was successful");
            }

            ConfigDB.closeConnection();
            return isDeleted;

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error 1521: " + e.getMessage());
        }

        return isDeleted;
    }

    @Override
    public Object findById(int id) {
        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        Appointment objAppointment = null;
        try{
            //2. SQL Sentence
            String sql = "SELECT * FROM appointments WHERE appointments.id = ?";
            //3. Preparing statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //4.
            objPrepare.setInt(1,id);
            //5. execute
            ResultSet objResult = objPrepare.executeQuery();
            /*
        CREATE TABLE appointments(
        id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
        date_appointment DATE NOT NULL,
        time_appointment TIME NOT NULL,
        reason varchar(40) NOT NULL,
        id_patient int NOT NULL,
        FOREIGN KEY (id_patient) REFERENCES patients(id),
        id_doctor int NOT NULL,
        FOREIGN KEY (id_doctor) REFERENCES doctors(id)
        );
        * */
            while (objResult.next()){
                objAppointment = new Appointment();
                objAppointment.setId(objResult.getInt("id"));
                objAppointment.setDate_appointment(objResult.getString("date_appointment"));
                objAppointment.setTime_appointment(objResult.getString("time_appointment"));
                objAppointment.setReason(objResult.getString("reason"));
                objAppointment.setId_patient(objResult.getInt("id_patient"));
                objAppointment.setId_doctor(objResult.getInt("id_doctor"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objAppointment;
    }

    public List<Object> findAll() {

        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Initialize a list to save data.
        List<Object> listAppointments = new ArrayList<>();

        try{
            //3. Write sentence:
            String sql = "SELECT * FROM appointments INNER JOIN patients ON appointments.id_patient = patients.id INNER JOIN doctors ON appointments.id_doctor = doctors.id ORDER BY appointments.id ASC;";
            //4. Use preparedStatement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5. Execute
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6. Get results
            while(objResult.next()){
                Appointment objAppointment = new Appointment();
                objAppointment.setId(objResult.getInt("id"));
                objAppointment.setDate_appointment(objResult.getString("date_appointment"));
                objAppointment.setTime_appointment(objResult.getString("time_appointment"));
                objAppointment.setReason(objResult.getString("reason"));
                objAppointment.setId_patient(objResult.getInt("id_patient"));
                objAppointment.setName_patient(objResult.getString("patients.name"));
                objAppointment.setId_doctor(objResult.getInt("id_doctor"));
                objAppointment.setName_doctor(objResult.getString("doctors.name"));

                listAppointments.add(objAppointment);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }

        //7. Close connection
        ConfigDB.closeConnection();

        return listAppointments;
    }
}
