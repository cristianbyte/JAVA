package controller;

import entity.Passenger;
import model.PassengerModel;

import javax.swing.*;
import java.util.List;

public class PassengerController {

    PassengerModel objPatientModel = new PassengerModel();

    private String creatingQuestion( String message,String title){
        return JOptionPane.showInputDialog(null,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE
                );
    }

    public void create() {

        Passenger objPassenger = new Passenger();

        String name = creatingQuestion("Name: ","Creating Passenger");
        objPassenger.setName(name);
        String lastName = creatingQuestion("Last Name: ","Creating Passenger");
        objPassenger.setLast_name(lastName);

        String identity = JOptionPane.showInputDialog("Enter your identity: ");
        while (!identity.matches("\\d+")) {
            identity = JOptionPane.showInputDialog("Please enter a valid number:");
        }
        objPassenger.setIdentity(identity);

        objPassenger = (Passenger) this.objPatientModel.create(objPassenger);

        JOptionPane.showMessageDialog(null, objPassenger.toString());
  }

    public void getAll(){
        String list = this.getAll(objPatientModel.findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public String getAll(List listObject){
        StringBuilder list = new StringBuilder("List Passenger: \n");
        for(Object obj : listObject){
            Passenger objCoder = (Passenger) obj;
            list.append(objCoder.toString()).append("\n");
        }
        return list.toString();
    }

    public void update(){
        String listPatient = this.getAll(this.objPatientModel.findAll());

        int idUpdated = Integer.parseInt(JOptionPane.showInputDialog(listPatient + "\n Enter the ID to edit."));

        Passenger objPassenger = (Passenger) this.objPatientModel.findById(idUpdated);

        if(objPassenger == null){
            JOptionPane.showMessageDialog(null,"Passenger not found.");
        }else{
            String name = JOptionPane.showInputDialog(null,"Enter Passenger name:", objPassenger.getName());
            String lastName = JOptionPane.showInputDialog(null, "Enter Passenger last name:", objPassenger.getLast_name());

            String identity = JOptionPane.showInputDialog(null,"Enter Passenger identity:", objPassenger.getIdentity());
            while (!identity.matches("\\d+")) {
                identity = JOptionPane.showInputDialog("Please enter a valid number:");
            }

            objPassenger.setName(name);
            objPassenger.setLast_name(lastName);
            objPassenger.setIdentity(identity);

            this.objPatientModel.update(objPassenger);
        }
    }

    public void delete(){
        String listPatient = this.getAll(this.objPatientModel.findAll());

        int confirm;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPatient + "Enter the ID to delete"));
        Passenger objPassenger = (Passenger) this.objPatientModel.findById(idDelete);

        if(objPassenger == null){
            JOptionPane.showMessageDialog(null,"Passenger not found.");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Sure? You want to delete this Passenger? \n"+ objPassenger.toString(), "Deleting Passenger",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION){
                this.objPatientModel.delete(objPassenger);
            }
        }
    }
}
