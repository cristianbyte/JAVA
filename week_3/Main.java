
import controller.AppointmentController;
import controller.PatientController;
import controller.DoctorController;
import controller.SpecialtyController;
import entity.Appointment;
import entity.Doctor;

import javax.swing.*;
import java.util.Objects;
/*
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
* */

public class Main {
    public static void main(String[] args) {

        PatientController objPatientController = new PatientController();
        DoctorController objDoctorController = new DoctorController();
        SpecialtyController objSpecialityController = new SpecialtyController();
        AppointmentController objAppointmentController = new AppointmentController();

        Object[] optionsMenu = {"Manage Patients","Manage Doctors","Manage Appointments","Manage Specialities","Exit"};
        Object seleccion;

        do{
            seleccion = JOptionPane.showInputDialog(
                    null,
                    "Select an option: ",
                    "Menu",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    optionsMenu,
                    optionsMenu[0]);
            if(seleccion == null){
                break;
            }

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
                        if(seleccion == null){
                            break;
                        }

                        switch ((String) seleccion){
                            case "Create Patient":
                                objPatientController.create();
                                break;
                            case "List Patients":
                                objPatientController.getAll();
                                break;
                            case "Update Patient":
                                objPatientController.update();
                                break;
                            case "Delete Patient":
                                objPatientController.delete();
                                break;
                        }
                    }while(!Objects.equals(seleccion,"Exit") || seleccion == null);
                    break;
                case "Manage Doctors":
                    Object[] optionsDoctors = { "Create Doctor","List Doctor","Update Doctor","Delete Doctor","Exit"};
                    do{
                        seleccion = JOptionPane.showInputDialog(
                                new JFrame(),
                                "Select an option: ",
                                "Doctors",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                optionsDoctors,
                                optionsDoctors[0]);
                        if(seleccion == null){
                            break;
                        }
                        switch ((String) seleccion){
                            case "Create Doctor":
                                objDoctorController.create();
                                break;
                            case "List Doctor":
                                objDoctorController.getAll();
                                break;
                            case "Update Doctor":
                                objDoctorController.update();
                                break;
                            case "Delete Doctor":
                                objDoctorController.delete();
                                break;
                        }
                    }while(!Objects.equals(seleccion,"Exit"));
                    break;
                case "Manage Specialities":
                    Object[] optionsSpecialities = { "Create Speciality","List Speciality","Update Speciality","Delete Speciality","Exit"};
                    do{
                        seleccion = JOptionPane.showInputDialog(
                                new JFrame(),
                                "Select an option: ",
                                "Doctors",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                optionsSpecialities,
                                optionsSpecialities[0]);
                        if(seleccion == null){
                            break;
                        }
                        switch ((String) seleccion){
                            case "Create Speciality":
                                objSpecialityController.create();
                                break;
                            case "List Speciality":
                                objSpecialityController.getAll();
                                break;
                            case "Update Speciality":
                                objSpecialityController.update();
                                break;
                            case "Delete Speciality":
                                objSpecialityController.delete();
                                break;
                        }
                    }while(!Objects.equals(seleccion,"Exit"));
                    break;
                case "Manage Appointments":
                    Object[] optionsAppointments = { "Create Appointments","List Appointments","Update Appointments","Delete Appointments","Exit"};
                    do{
                        seleccion = JOptionPane.showInputDialog(
                                new JFrame(),
                                "Select an option: ",
                                "Appointments",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                optionsAppointments,
                                optionsAppointments[0]);
                        if(seleccion == null){
                            break;
                        }
                        switch ((String) seleccion){
                            case "Create Appointments":
                                //objAppointmentController.create();
                                break;
                            case "List Appointments":
                                objAppointmentController.getAll();
                                break;
                            case "Update Appointments":
                                //objAppointmentController.update();
                                break;
                            case "Delete Appointments":
                                //objAppointmentController.delete();
                                break;
                        }
                    }while(!Objects.equals(seleccion,"Exit"));
                    break;
            }
        }while(!Objects.equals(seleccion, "Exit"));
    }
}