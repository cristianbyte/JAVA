package controller;

import entity.Booking;
import entity.Flight;
import model.AirplaneModel;
import model.BookingModel;
import model.FlightModel;
import model.PassengerModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BookingController {

    BookingModel objBookingModel = new BookingModel();

    private String creatingQuestion( String message,String title){
        return JOptionPane.showInputDialog(null,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE
        );
    }

    private List<Flight> getAllFlights(){
        FlightModel objFlightModel = new FlightModel();
        List<Flight> listFlights = null;
        List<Object> allFlights = objFlightModel.findAll();
        if(allFlights != null){
            listFlights = new ArrayList<>();
            for(Object objF : objFlightModel.findAll()){
                Flight objFlight = (Flight) objF;
                listFlights.add(objFlight);

            }
        }
        return  listFlights;
    }

    private String[] getSeatsList(int airplaneCapacity){

        /* continue listing seats */
        String[] seatsList = new String[airplaneCapacity];
        char sixthChar = '@';
        try{
            for (int i = 0; i < airplaneCapacity ; i++){
                if( i % 10 == 0 ) sixthChar++;
                seatsList[i] = Character.toString(sixthChar).concat(Integer.toString(i+10));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return seatsList;
    }

    public void create() {

        Booking objBooking = new Booking();

        List<Flight> listFlights = getAllFlights();
            /*CREATE TABLE bookings(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    booking_date DATE NOT NULL, --------- ✅
    seat varchar(40) NOT NULL,  ---------   ✅
    id_passenger int NOT NULL,
    FOREIGN KEY (id_passenger) REFERENCES passengers(id),
    id_flight int NOT NULL, ✅
    FOREIGN KEY (id_flight) REFERENCES flights(id)
    );*/
        if(listFlights!= null){
            String[] departureList = new String[listFlights.size()];
            Flight objFlight = new Flight();
            for (int i = 0; i < listFlights.size(); i++) {
                Flight flight = listFlights.get(i);
                departureList[i] = flight.getDestination();
            }

            Object departureSelected = JOptionPane.showInputDialog(
                    null,
                    "Select an option: ",
                    "Medellin to:",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    departureList,
                    departureList[0]);
            /* get the Flight object by the selected destination */
            Flight selectedFlight = listFlights.get(Arrays.asList(departureList).indexOf((String) departureSelected));
            objBooking.setBooking_date(selectedFlight.getDeparture_date());
            objBooking.setId_flight(selectedFlight.getId());

            /* seating logic: searching airplane capacity */
            for (int i = 0; i < listFlights.size(); i++) {
                if( departureList[i] == departureSelected) objFlight = listFlights.get(i);
            }
            int airplaneCapacity = objFlight.getAirplane_capacity();
            /* List seats A10-20, B10-B20... */
            String[] seatsList = getSeatsList(airplaneCapacity);
            /* show only available seats  */
            BookingModel objBM = new BookingModel();
            List<Object> listBooking = objBM.findAll();
            /* validate airplane capacity by id*/
            List<Booking> seatListByAirplane = new ArrayList<>();
            for(Object obj : listBooking ){
                Booking ownAirplane = (Booking) obj;
                if(ownAirplane.getId_flight() == objBooking.getId_flight()){
                    seatListByAirplane.add(ownAirplane);
                }
            }

            String[] listSeats = new String[seatListByAirplane.size()];

            for(int itm = 0; itm < seatListByAirplane.size() -1 ; itm++){
                Booking booking = (Booking) seatListByAirplane.get(itm);
                listSeats[itm] = booking.getSeat();
            }
            /* removing occupied seats */
            for(String elem : listSeats ){
                seatsList = Arrays.stream(seatsList)
                        .filter(e -> !Objects.equals(e, elem))
                        .toArray(String[]::new);
            }

            if(seatsList.length == 0 ) {
                JOptionPane.showMessageDialog(null,"There's no more seats for this Flight");
            }else{
                Object seatSelected = JOptionPane.showInputDialog(
                        null,
                        "Select an option: ",
                        "Seats available seats:",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        seatsList,
                        seatsList[0]);
                objBooking.setSeat((String) seatSelected);

                PassengerModel objPM = new PassengerModel();
                PassengerController objPC = new PassengerController();

                objBooking.setId_passenger(Integer.parseInt(JOptionPane.showInputDialog(null, STR."Select the Passenger's ID: \n \{objPC.getAll(objPM.findAll())}")));

                objBooking = (Booking) this.objBookingModel.create(objBooking);
                JOptionPane.showMessageDialog(null, objBooking.toString(false));
            }

        }else{
            JOptionPane.showMessageDialog(null,"There's no Flights available.");
        }
    }
    
    public void getAll(){
        String list = this.getAll(objBookingModel.findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public String getAll(List listObject){
        StringBuilder list = new StringBuilder("List : \n");
        for(Object obj : listObject){
            Booking objBooking = (Booking) obj;
            list.append(objBooking.toString(true)).append("\n");
        }
        return list.toString();
    }

    public void update(){

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
