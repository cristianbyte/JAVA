package controller;

import entity.Patient;
import model.PatientModel;

import javax.swing.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.Objects;

public class PatientController {

    PatientModel objPatienModel = new PatientModel();

    private String creatingQuestion( String message,String title){
        return JOptionPane.showInputDialog(null,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE);
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

        objPatient = (Patient) this.objPatienModel.create(objPatient);

        JOptionPane.showMessageDialog(null, objPatient.toString());
  }
}
