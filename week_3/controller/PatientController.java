package controller;

import entity.Patient;
import model.PatientModel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

public class PatientController {

    PatientModel objPatientModel = new PatientModel();

    private String creatingQuestion( String message,String title){
        return JOptionPane.showInputDialog(null,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE
                );
    }

    public void create() {

        Patient objPatient = new Patient();

        String name = creatingQuestion("Name: ","Creating Patient");
        objPatient.setName(name);
        String lastName = creatingQuestion("Last Name: ","Creating Patient");
        objPatient.setLast_name(lastName);
        String birth_date = JOptionPane.showInputDialog("Enter your date of birth: yyyy-mm-dd");
        while (!birth_date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            birth_date = JOptionPane.showInputDialog("Please enter a valid date: yyyy-mm-dd");
        }
        objPatient.setBirth_date(birth_date);
        //ALL EYES ON ME! ↓↓↓↓↓↓ Be careful ↓↓↓↓↓↓
        String identity = JOptionPane.showInputDialog("Enter your identity: ");
        while (!identity.matches("\\d+")) {
            identity = JOptionPane.showInputDialog("Please enter a valid number:");
        }
        objPatient.setIdentity(identity);

        objPatient = (Patient) this.objPatientModel.create(objPatient);

        JOptionPane.showMessageDialog(null, objPatient.toString());
  }

    public void getAll(){
        String list = this.getAll(objPatientModel.findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public String getAll(List listObject){
        StringBuilder list = new StringBuilder("List Patients: \n");
        for(Object obj : listObject){
            Patient objCoder = (Patient) obj;
            list.append(objCoder.toString()).append("\n");
        }
        return list.toString();
    }

    public void update(){
        String listPatient = this.getAll(this.objPatientModel.findAll());

        int idUpdated = Integer.parseInt(JOptionPane.showInputDialog(listPatient + "\n Enter the ID to edit."));

        Patient objPatient = (Patient) this.objPatientModel.findById(idUpdated);

        if(objPatient == null){
            JOptionPane.showMessageDialog(null,"Patient not found.");
        }else{
            /*CREATE TABLE patients(
            id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
            name varchar(40) NOT NULL,
            last_name varchar(40) NOT NULL,
            birth_date DATE NOT NULL,
            identity varchar(40) NOT NULL
            );*/
            String name = JOptionPane.showInputDialog(null,"Enter patient name:",objPatient.getName());
            String lastName = JOptionPane.showInputDialog(null, "Enter patient last name:",objPatient.getLast_name());
            String birth_date = JOptionPane.showInputDialog(null,"Enter birth date's patient:",objPatient.getBirth_date());
            while (!birth_date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                birth_date = JOptionPane.showInputDialog(null,"Please enter a valid date: yyyy-mm-dd");
            }

            String identity = JOptionPane.showInputDialog(null,"Enter patient identity:",objPatient.getIdentity());
            while (!identity.matches("\\d+")) {
                identity = JOptionPane.showInputDialog("Please enter a valid number:");
            }

            objPatient.setName(name);
            objPatient.setLast_name(lastName);
            objPatient.setBirth_date(birth_date);
            objPatient.setIdentity(identity);

            this.objPatientModel.update(objPatient);
        }
    }

    public void delete(){
        String listPatient = this.getAll(this.objPatientModel.findAll());

        int confirm;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPatient + "Enter the ID to delete"));
        Patient objPatient = (Patient) this.objPatientModel.findById(idDelete);

        if(objPatient == null){
            JOptionPane.showMessageDialog(null,"Patient not found.");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Sure? You want to delete this patient? \n"+objPatient.toString(), "Deleting Patient",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION){
                this.objPatientModel.delete(objPatient);
            }
        }
    }
}
