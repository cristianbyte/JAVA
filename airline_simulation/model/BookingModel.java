package model;

import controller.FlightController;
import controller.PassengerController;
import database.CRUD;
import database.ConfigDB;
import entity.Booking;
import entity.Flight;
import entity.Passenger;

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
            String sql = "INSERT INTO bookings (booking_date,seat,id_passenger,id_flight) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, booking.getBooking_date());
            objPrepare.setString(2, booking.getSeat());
            objPrepare.setInt(3, booking.getId_passenger());
            objPrepare.setInt(4, booking.getId_flight());

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
            String sql = "UPDATE bookings SET booking_date = ? , seat = ? , id_passenger = ? , id_flight = ?  WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1, objBooking.getBooking_date());
            objPrepared.setString(2, objBooking.getSeat());
            objPrepared.setInt(3, objBooking.getId_passenger());
            objPrepared.setInt(4, objBooking.getId_flight());

            objPrepared.setInt(5, objBooking.getId());

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

            while (objResult.next()){
                objBooking = new Booking();
                objBooking.setId(objResult.getInt("id"));
                objBooking.setBooking_date(objResult.getString("booking_date"));
                objBooking.setSeat(objResult.getString("seat"));
                objBooking.setId_passenger(objResult.getInt("id_passenger"));
                objBooking.setId_flight(objResult.getInt("id_flight"));
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
        List<Object> listBookings = new ArrayList<>();
            /*CREATE TABLE bookings(
            id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
            booking_date DATE NOT NULL,
            seat varchar(40) NOT NULL,
            id_passenger int NOT NULL,
            FOREIGN KEY (id_passenger) REFERENCES passengers(id),
            id_flight int NOT NULL,
            FOREIGN KEY (id_flight) REFERENCES flights(id)
            );*/
        try{
            //3. Write sentence:
            String sql = "SELECT * FROM bookings INNER JOIN passengers ON bookings.id_passenger = passengers.id INNER JOIN flights ON bookings.id_flight = flights.id ORDER BY bookings.id ASC;";
            //4. Use preparedStatement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5. Execute
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6. Get results
            while(objResult.next()){
                Booking objBooking = new Booking();
                objBooking.setId(objResult.getInt("id"));
                objBooking.setBooking_date(objResult.getString("booking_date"));
                objBooking.setSeat(objResult.getString("seat"));
                objBooking.setId_passenger(objResult.getInt("id_passenger"));
                objBooking.setId_flight(objResult.getInt("id_flight"));
                objBooking.setName_passenger(objResult.getString("passengers.name"));
                objBooking.setDestination(objResult.getString("flights.destination"));

                listBookings.add(objBooking);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, STR."Data acquisition error: \{e.getMessage()}");
        }

        //7. Close connection
        ConfigDB.closeConnection();

        return listBookings;
    }
}
