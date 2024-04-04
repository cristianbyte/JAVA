package controller;

import entity.Flight;
import model.FlightModel;
import model.AirplaneModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.StringTemplate.STR;

/*CREATE TABLE flights(
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
destination varchar(40) NOT NULL,
departure_date DATE  NOT NULL,
departure_time TIME NOT NULL,
id_airplane INT NOT NULL,
FOREIGN KEY (id_airplane) REFERENCES airplanes(id)
);
* */

public class FlightController {

    FlightModel objFlightModel = new FlightModel();
    AirplaneController objAirplaneController = new AirplaneController();
    AirplaneModel objAM = new AirplaneModel();

    private String creatingQuestion( String message,String title){
        return JOptionPane.showInputDialog(null,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE
        );
    }


/*        for(Object obj : listObject){
            Flight objFlight = (Flight) obj;
            list.append(objFlight.toString(true)).append("\n");
        }*/

    public void create() {

        Flight objFlight = new Flight();

        String destination = creatingQuestion("Destination: ","Creating Flight");
        objFlight.setDestination(destination);
        String departure_date = creatingQuestion("Departure Date: yyyy-mm-dd","Creating Flight");
        while (!departure_date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            departure_date = JOptionPane.showInputDialog("Please enter a valid date: yyyy-mm-dd");
        }
        objFlight.setDeparture_date(departure_date);
        String departure_time = creatingQuestion("Departure Time: hh:mm:ss","Creating Flight");
        objFlight.setDeparture_time(departure_time);

        int id_airplane = Integer.parseInt(JOptionPane.showInputDialog(null, STR."Airplanes avaliables:  \n\{this.getAll(objFlightModel.listAirplanesAvailable(departure_date))} \nSelect the Airplane's ID:"));
        objFlight.setId_airplane(id_airplane);

        objFlight = (Flight) this.objFlightModel.create(objFlight);

        JOptionPane.showMessageDialog(null, objFlight.toString());
    }

    public void getAll(){
        String list = this.getAll(objFlightModel.findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public String getAll(List listObject){
        StringBuilder list = new StringBuilder("List: \n");
        for(Object obj : listObject){
            Flight objFlight = (Flight) obj;
            list.append(objFlight.toString(true)).append("\n");
        }
        return list.toString();
    }

    public void update(){
        String listFlight = this.getAll(this.objFlightModel.findAll());

        int idUpdated = Integer.parseInt(JOptionPane.showInputDialog(listFlight + "\n Enter the ID to edit."));

        Flight objFlight = (Flight) this.objFlightModel.findById(idUpdated);

        if(objFlight == null){
            JOptionPane.showMessageDialog(null,"Flight not found.");
        }else{

            String destination = JOptionPane.showInputDialog(null,"Enter destination:", objFlight.getDestination());
            String departure_date = JOptionPane.showInputDialog(null, "Enter new departure date:", objFlight.getDeparture_date());
            String departure_time = JOptionPane.showInputDialog(null, "Enter new departure time:", objFlight.getDeparture_time());
            int id_airplane =  Integer.parseInt( JOptionPane.showInputDialog(null, STR."Specialties:  \n \{objAirplaneController.getAll((objAM.findAll()))} \nSelect the new Airplane's ID:", objFlight.getId_airplane()));

            /*CREATE TABLE flights(
            id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
            destination varchar(40) NOT NULL,
            departure_date DATE  NOT NULL,
            departure_time TIME NOT NULL,
            id_airplane INT NOT NULL,
            FOREIGN KEY (id_airplane) REFERENCES airplanes(id)
            );
            * */

            objFlight.setDestination(destination);
            objFlight.setDeparture_date(departure_date);
            objFlight.setDeparture_time(departure_time);
            objFlight.setId_airplane(id_airplane);

            this.objFlightModel.update(objFlight);
        }
    }

    public void delete(){
        String listFlight = this.getAll(this.objFlightModel.findAll());

        int confirm;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listFlight + "Enter the ID to delete"));
        Flight objFlight = (Flight) this.objFlightModel.findById(idDelete);

        if(objFlight == null){
            JOptionPane.showMessageDialog(null,"Flight not found.");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Sure? You want to delete this Flight? \n"+ objFlight.toString(), "Deleting Flight",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION){
                this.objFlightModel.delete(objFlight);
            }
        }
    }
}
