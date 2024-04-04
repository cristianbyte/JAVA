package model;

import database.CRUD;
import database.ConfigDB;
import entity.Booking;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingModel implements CRUD {
    @Override
    public Object create(Object object) {
        JOptionPane.showMessageDialog(null,object.toString());
        Connection objConnection = ConfigDB.openConnection();
        Booking booking = (Booking) object;

        try {
            String sql = "INSERT INTO bookings (date_booking,time_booking,reason,id_patient,id_doctor) VALUES (?,?,?,?,?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, booking.getDate_booking());
            objPrepare.setString(2, booking.getTime_booking());
            objPrepare.setString(3, booking.getReason());
            objPrepare.setInt(4, booking.getId_patient());
            objPrepare.setInt(5, booking.getId_doctor());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                booking.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Success!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error  6626: " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return booking;
    }

    @Override
    public List<Object> read(Object object) {
        return null;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Booking objBooking = (Booking) object;
        boolean isUpdated = false;

        try{
            String sql = "UPDATE bookings SET date_booking = ? , time_booking = ? , reason = ? , id_patient = ? , id_doctor = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1, objBooking.getDate_booking());
            objPrepared.setString(2, objBooking.getTime_booking());
            objPrepared.setString(3, objBooking.getReason());
            objPrepared.setInt(4, objBooking.getId_patient());
            objPrepared.setInt(5, objBooking.getId_doctor());

            objPrepared.setInt(6, objBooking.getId());

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
        Booking objBooking = (Booking) object;
        //2. Elimination state
        boolean isDeleted = false;
        //3.Open connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Write sentence
            String sql = "DELETE FROM bookings WHERE bookings.id = ?;";
            //5.
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //6.
            objPrepare.setInt(1, objBooking.getId());

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
        Booking objBooking = null;
        try{
            //2. SQL Sentence
            String sql = "SELECT * FROM bookings WHERE bookings.id = ?";
            //3. Preparing statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //4.
            objPrepare.setInt(1,id);
            //5. execute
            ResultSet objResult = objPrepare.executeQuery();
            /*
        CREATE TABLE bookings(
        id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
        date_booking DATE NOT NULL,
        time_booking TIME NOT NULL,
        reason varchar(40) NOT NULL,
        id_patient int NOT NULL,
        FOREIGN KEY (id_patient) REFERENCES patients(id),
        id_doctor int NOT NULL,
        FOREIGN KEY (id_doctor) REFERENCES doctors(id)
        );
        * */
            while (objResult.next()){
                objBooking = new Booking();
                objBooking.setId(objResult.getInt("id"));
                objBooking.setDate_booking(objResult.getString("date_booking"));
                objBooking.setTime_booking(objResult.getString("time_booking"));
                objBooking.setReason(objResult.getString("reason"));
                objBooking.setId_patient(objResult.getInt("id_patient"));
                objBooking.setId_doctor(objResult.getInt("id_doctor"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objBooking;
    }

    public List<Object> findAll() {

        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Initialize a list to save data.
        List<Object> listbookings = new ArrayList<>();

        try{
            //3. Write sentence:
            String sql = "SELECT * FROM bookings INNER JOIN patients ON bookings.id_patient = patients.id INNER JOIN doctors ON bookings.id_doctor = doctors.id ORDER BY bookings.id ASC;";
            //4. Use preparedStatement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5. Execute
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6. Get results
            while(objResult.next()){
                Booking objBooking = new Booking();
                objBooking.setId(objResult.getInt("id"));
                objBooking.setDate_booking(objResult.getString("date_booking"));
                objBooking.setTime_booking(objResult.getString("time_booking"));
                objBooking.setReason(objResult.getString("reason"));
                objBooking.setId_patient(objResult.getInt("id_patient"));
                objBooking.setName_patient(objResult.getString("patients.name"));
                objBooking.setId_doctor(objResult.getInt("id_doctor"));
                objBooking.setName_doctor(objResult.getString("doctors.name"));

                listbookings.add(objBooking);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }

        //7. Close connection
        ConfigDB.closeConnection();

        return listbookings;
    }
}
