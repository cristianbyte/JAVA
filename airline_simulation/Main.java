
import controller.BookingController;
import controller.PassengerController;
import controller.FlightController;
import controller.AirplaneController;

import javax.swing.*;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        PassengerController objPassengerController = new PassengerController();
        FlightController objFlightController = new FlightController();
        AirplaneController objAirplaneController = new AirplaneController();
        BookingController objBookingController = new BookingController();

        Object[] optionsMenu = {"Manage Airplanes","Manage Flights","Manage Bookings","Manage Passengers","Exit"};
        Object seleccion;

        do{
            seleccion = JOptionPane.showInputDialog(
                    null,
                    "Select an option: ",
                    "Welcome to JokeJet",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    optionsMenu,
                    optionsMenu[0]);
            if(seleccion == null){
                break;
            }

            switch ((String) seleccion){
                case "Manage Passengers":
                    Object[] optionsPassengers = { "Create Passenger","List Passengers","Update Passenger","Delete Passenger","Exit"};
                    do{
                        seleccion = JOptionPane.showInputDialog(
                                new JFrame(),
                                "Select an option: ",
                                "Passengers",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                optionsPassengers,
                                optionsPassengers[0]);
                        if(seleccion == null){
                            break;
                        }

                        switch ((String) seleccion){
                            case "Create Passenger":
                                objPassengerController.create();
                                break;
                            case "List Passengers":
                                objPassengerController.getAll();
                                break;
                            case "Update Passenger":
                                objPassengerController.update();
                                break;
                            case "Delete Passenger":
                                objPassengerController.delete();
                                break;
                        }
                    }while(!Objects.equals(seleccion,"Exit") || seleccion == null);
                    break;
                case "Manage Flights":
                    Object[] optionsFlights = { "Create Flight","List Flight","Update Flight","Delete Flight","Exit"};
                    do{
                        seleccion = JOptionPane.showInputDialog(
                                new JFrame(),
                                "Select an option: ",
                                "Flights",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                optionsFlights,
                                optionsFlights[0]);
                        if(seleccion == null){
                            break;
                        }
                        switch ((String) seleccion){
                            case "Create Flight":
                                objFlightController.create();
                                break;
                            case "List Flight":
                                objFlightController.getAll();
                                break;
                            case "Update Flight":
                                objFlightController.update();
                                break;
                            case "Delete Flight":
                                objFlightController.delete();
                                break;
                        }
                    }while(!Objects.equals(seleccion,"Exit"));
                    break;
                case "Manage Airplanes":
                    Object[] optionsAirplane = { "Create Airplane","List Airplane","Delete Airplane","Exit"};
                    do{
                        seleccion = JOptionPane.showInputDialog(
                                new JFrame(),
                                "Select an option: ",
                                "Airplanes",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                optionsAirplane,
                                optionsAirplane[0]);
                        if(seleccion == null){
                            break;
                        }
                        switch ((String) seleccion){
                            case "Create Airplane":
                                objAirplaneController.create();
                                break;
                            case "List Airplane":
                                objAirplaneController.getAll();
                                break;
                            case "Delete Airplane":
                                objAirplaneController.delete();
                                break;
                        }
                    }while(!Objects.equals(seleccion,"Exit"));
                    break;
                case "Manage Bookings":
                    Object[] optionsBooking = { "Create Bookings","List Bookings","Update Bookings","Delete Bookings","Exit"};
                    do{
                        seleccion = JOptionPane.showInputDialog(
                                new JFrame(),
                                "Select an option: ",
                                "Booking",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                optionsBooking,
                                optionsBooking[0]);
                        if(seleccion == null){
                            break;
                        }
                        switch ((String) seleccion){
                            case "Create Bookings":
                                objBookingController.create();
                                break;
                            case "List Bookings":
                                objBookingController.getAll();
                                break;
                            case "Update Bookings":
                                //objBookingController.update();
                                break;
                            case "Delete Bookings":
                                objBookingController.delete();
                                break;
                        }
                    }while(!Objects.equals(seleccion,"Exit"));
                    break;
            }
        }while(!Objects.equals(seleccion, "Exit"));
    }
}