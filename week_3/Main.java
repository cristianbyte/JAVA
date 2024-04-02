
import controller.PatientController;

import javax.swing.*;
import java.util.Objects;

/*CREATE TABLE specialties(
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
name varchar(40) NOT NULL,
description varchar(40) NOT NULL
);

CREATE TABLE doctors(
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
name varchar(40) NOT NULL,
last_name varchar(40) NOT NULL,
id_specialty int NOT NULL,
FOREIGN KEY (id_specialty) REFERENCES specialties(id)
);

CREATE TABLE appointments(
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
date_appointment DATE NOT NULL,
time_oppointment TIME NOT NULL,
reason varchar(40) NOT NULL,
id_patient int NOT NULL,
FOREIGN KEY (id_patient) REFERENCES patients(id),
id_doctor int NOT NULL,
FOREIGN KEY (id_doctor) REFERENCES doctors(id)
);
*
* */

public class Main {
    public static void main(String[] args) {

        PatientController objPatientController = new PatientController();

        Object[] optionsMenu = {"Manage Patients","Manage Doctors","Manage Appointments","Manage Specialities"};
        Object seleccion;

        do{
            seleccion = JOptionPane.showInputDialog(
                    new JFrame(),
                    "Select an option: ",
                    "Menu",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    optionsMenu,
                    optionsMenu[0]);

            switch ((String) seleccion){
                case "Manage Patients":
                    Object[] optionsPatients = { "Create Patient","List Patients","Update Patient","Delete Patient","Exit"};
                    do{
                        seleccion = JOptionPane.showInputDialog(
                                new JFrame(),
                                "Select an option: ",
                                "Patients",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                optionsPatients,
                                optionsPatients[0]);
                        switch ((String) seleccion){
                            case "Create Patient":
                                objPatientController.create();
                                break;
                            case "List Patients":
                                objPatientController.create();
                                break;
                            case "Update Patient":
                                objPatientController.create();
                                break;
                            case "Delete Patient":
                                objPatientController.create();
                                break;
                        }
                    }while(!Objects.equals(seleccion,null));
                    break;
            }
        }while(!Objects.equals(seleccion, null));

    }
}