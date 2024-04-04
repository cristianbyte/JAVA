package controller;

import entity.Booking;
import model.BookingModel;
import model.FlightModel;
import model.PassengerModel;

import javax.swing.*;
import java.util.List;

public class BookingController {

    BookingModel objBookingModel = new BookingModel();

    private String creatingQuestion( String message,String title){
        return JOptionPane.showInputDialog(null,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE
        );
    }

    public void create() {

        Booking objBooking = new Booking();
        PassengerController objPassengerController = new PassengerController();
        PassengerModel objPM = new PassengerModel();
        FlightController objFlightController = new FlightController();
        FlightModel objDC = new FlightModel();

        String date_booking = JOptionPane.showInputDialog("Enter date booking: yyyy-mm-dd");
        while (!date_booking.matches("\\d{4}-\\d{2}-\\d{2}")) {
            date_booking = JOptionPane.showInputDialog("Please enter a valid date: yyyy-mm-dd");
        }
        objBooking.setDate_booking(date_booking);

        Object[] optionsMenu = {"09:00:00","10:00:00","11:00:00","12:00:00","13:00:00","14:00:00","15:00:00","16:00:00","17:00:00","18:00:00","19:00:00","20:00:00",};
        Object time_booking = JOptionPane.showInputDialog(
                    null,
                    "Select an option: ",
                    "Menu",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    optionsMenu,
                    optionsMenu[0]);
        objBooking.setTime_booking((String) time_booking);

        String reason = creatingQuestion("Reason:","Creating Booking");
        objBooking.setReason(reason);

        int id_patient = Integer.parseInt(JOptionPane.showInputDialog(null, STR."Selec Patient's ID: \n\{objPassengerController.getAll(objPM.findAll())}"));
        objBooking.setId_patient(id_patient);
        int id_doctor = Integer.parseInt(JOptionPane.showInputDialog(null, STR."Selec Doctor's ID: \n\{objFlightController.getAll(objDC.findAll())}"));
        objBooking.setId_doctor(id_doctor);

        objBooking = (Booking) this.objBookingModel.create(objBooking);
        JOptionPane.showMessageDialog(null, objBooking.toString(false));
    }
    
    public void getAll(){
        String list = this.getAll(objBookingModel.findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public String getAll(List listObject){
        StringBuilder list = new StringBuilder("List Specialties: \n");
        for(Object obj : listObject){
            Booking objBooking = (Booking) obj;
            list.append(objBooking.toString(true)).append("\n");
        }
        return list.toString();
    }

    public void update(){
        FlightController objFlightController = new FlightController();
        FlightModel objDC = new FlightModel();
        String listBooking = this.getAll(this.objBookingModel.findAll());

        int idUpdated = Integer.parseInt(JOptionPane.showInputDialog(listBooking + "\n Enter the ID to edit."));

        Booking objBooking = (Booking) this.objBookingModel.findById(idUpdated);

        if(objBooking == null){
            JOptionPane.showMessageDialog(null,"Booking not found.");
        }else{
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
            String date_booking = JOptionPane.showInputDialog(null,"Enter the new date yyyy-mm-dd :", objBooking.getDate_booking());
            String time_booking = JOptionPane.showInputDialog(null, "Enter new time hh:mm:ss", objBooking.getTime_booking());
            String reason = JOptionPane.showInputDialog(null, "Enter new reason", objBooking.getReason());
            int id_doctor = Integer.parseInt(JOptionPane.showInputDialog(null, STR."Enter new Doctor's ID \n\{ objFlightController.getAll(objDC.findAll())}", objBooking.getId_doctor()));

            objBooking.setDate_booking(date_booking);
            objBooking.setTime_booking(time_booking);
            objBooking.setReason(reason);
            objBooking.setId_doctor(id_doctor);

            this.objBookingModel.update(objBooking);
        }
    }

    public void delete(){
        String listBookings = this.getAll(this.objBookingModel.findAll());

        int confirm;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listBookings + "Enter the ID to delete"));
        Booking objBooking = (Booking) this.objBookingModel.findById(idDelete);

        if(objBooking == null){
            JOptionPane.showMessageDialog(null,"Booking not found.");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Sure? You want to delete this Booking? \n"+ objBooking.toString(false), "Deleting Booking",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION){
                this.objBookingModel.delete(objBooking);
            }
        }
    }
}
