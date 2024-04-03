package controller;

import entity.Doctor;
import model.DoctorModel;
import model.SpecialtyModel;
import controller.SpecialtyController;

import javax.swing.*;
import java.util.List;

public class DoctorController {

    DoctorModel objDoctorModel = new DoctorModel();
    SpecialtyController objSpecialtiesController = new SpecialtyController();
    SpecialtyModel objSM = new SpecialtyModel();

    private String creatingQuestion( String message,String title){
        return JOptionPane.showInputDialog(null,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE
        );
    }

    public void create() {

        Doctor objDoctor = new Doctor();

        String name = creatingQuestion("Name: ","Creating Doctor");
        objDoctor.setName(name);
        String lastName = creatingQuestion("Last Name: ","Creating Doctor");
        objDoctor.setLast_name(lastName);
        int id_specialty = Integer.parseInt(JOptionPane.showInputDialog(null, STR."Specialties:  \n \{objSpecialtiesController.getAll((objSM.findAll()))} \nSelect the Specialty's ID:"));
        objDoctor.setId_specialty(id_specialty);

        objDoctor = (Doctor) this.objDoctorModel.create(objDoctor);

        JOptionPane.showMessageDialog(null, objDoctor.toString());
    }

    public void getAll(){
        String list = this.getAll(objDoctorModel.findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public String getAll(List listObject){
        StringBuilder list = new StringBuilder("List Doctors: \n");
        for(Object obj : listObject){
            Doctor objDoctor = (Doctor) obj;

            list.append(objDoctor.toString()).append("\n");
        }
        return list.toString();
    }

    public void update(){
        String listDoctor = this.getAll(this.objDoctorModel.findAll());

        int idUpdated = Integer.parseInt(JOptionPane.showInputDialog(listDoctor + "\n Enter the ID to edit."));

        Doctor objDoctor = (Doctor) this.objDoctorModel.findById(idUpdated);

        if(objDoctor == null){
            JOptionPane.showMessageDialog(null,"Doctor not found.");
        }else{
            /*CREATE TABLE doctors(
        id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
        name varchar(40) NOT NULL,
        last_name varchar(40) NOT NULL,
        id_specialty int NOT NULL,
        FOREIGN KEY (id_specialty) REFERENCES specialties(id)
        );*/
            String name = JOptionPane.showInputDialog(null,"Enter patient name:",objDoctor.getName());
            String lastName = JOptionPane.showInputDialog(null, "Enter patient last name:",objDoctor.getLast_name());
            int id_specialty =  Integer.parseInt( JOptionPane.showInputDialog(null, STR."Specialties:  \n \{objSpecialtiesController.getAll((objSM.findAll()))} \nSelect the new Specialty's ID:",objDoctor.getId_specialty()));


            objDoctor.setName(name);
            objDoctor.setLast_name(lastName);
            objDoctor.setId_specialty(id_specialty);

            this.objDoctorModel.update(objDoctor);
        }
    }

    public void delete(){
        String listDoctor = this.getAll(this.objDoctorModel.findAll());

        int confirm;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listDoctor + "Enter the ID to delete"));
        Doctor objDoctor = (Doctor) this.objDoctorModel.findById(idDelete);

        if(objDoctor == null){
            JOptionPane.showMessageDialog(null,"Doctor not found.");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Sure? You want to delete this Doctor? \n"+objDoctor.toString(), "Deleting Doctor",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION){
                this.objDoctorModel.delete(objDoctor);
            }
        }
    }
}
