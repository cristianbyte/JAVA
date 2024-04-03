package controller;

import entity.Appointment;
import entity.Specialty;
import model.AppointmentModel;
import model.DoctorModel;
import model.PatientModel;

import javax.swing.*;
import java.util.List;

public class AppointmentController {

    AppointmentModel objAppointmentModel = new AppointmentModel();

    private String creatingQuestion( String message,String title){
        return JOptionPane.showInputDialog(null,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE
        );
    }

    public void create() {

        Appointment objAppointment = new Appointment();
        PatientController objPatientController= new PatientController();
        PatientModel objPM = new PatientModel();
        DoctorController objDoctorController= new DoctorController();
        DoctorModel objDC = new DoctorModel();

        String date_appointment = JOptionPane.showInputDialog("Enter date appointment: yyyy-mm-dd");
        while (!date_appointment.matches("\\d{4}-\\d{2}-\\d{2}")) {
            date_appointment = JOptionPane.showInputDialog("Please enter a valid date: yyyy-mm-dd");
        }
        objAppointment.setDate_appointment(date_appointment);

        Object[] optionsMenu = {"09:00:00","10:00:00","11:00:00","12:00:00","13:00:00","14:00:00","15:00:00","16:00:00","17:00:00","18:00:00","19:00:00","20:00:00",};
        Object time_appointment = JOptionPane.showInputDialog(
                    null,
                    "Select an option: ",
                    "Menu",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    optionsMenu,
                    optionsMenu[0]);
        objAppointment.setTime_appointment((String) time_appointment);

        String reason = creatingQuestion("Reason:","Creating Appointment");
        objAppointment.setReason(reason);

        int id_patient = Integer.parseInt(JOptionPane.showInputDialog(null, STR."Selec Patient's ID: \n\{objPatientController.getAll(objPM.findAll())}"));
        objAppointment.setId_patient(id_patient);
        int id_doctor = Integer.parseInt(JOptionPane.showInputDialog(null, STR."Selec Doctor's ID: \n\{objDoctorController.getAll(objDC.findAll())}"));
        objAppointment.setId_doctor(id_doctor);

        objAppointment = (Appointment) this.objAppointmentModel.create(objAppointment);
        JOptionPane.showMessageDialog(null, objAppointment.toString(false));
    }
    
    public void getAll(){
        String list = this.getAll(objAppointmentModel.findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public String getAll(List listObject){
        StringBuilder list = new StringBuilder("List Specialties: \n");
        for(Object obj : listObject){
            Appointment objAppointment = (Appointment) obj;
            list.append(objAppointment.toString(true)).append("\n");
        }
        return list.toString();
    }

    public void update(){
        DoctorController objDoctorController = new DoctorController();
        DoctorModel objDC = new DoctorModel();
        String listAppointment = this.getAll(this.objAppointmentModel.findAll());

        int idUpdated = Integer.parseInt(JOptionPane.showInputDialog(listAppointment + "\n Enter the ID to edit."));

        Appointment objAppointment = (Appointment) this.objAppointmentModel.findById(idUpdated);

        if(objAppointment == null){
            JOptionPane.showMessageDialog(null,"Appointment not found.");
        }else{
                /*
CREATE TABLE appointments(
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
date_appointment DATE NOT NULL,
time_appointment TIME NOT NULL,
reason varchar(40) NOT NULL,
id_patient int NOT NULL,
FOREIGN KEY (id_patient) REFERENCES patients(id),
id_doctor int NOT NULL,
FOREIGN KEY (id_doctor) REFERENCES doctors(id)
);
* */
            String date_appointment = JOptionPane.showInputDialog(null,"Enter the new date yyyy-mm-dd :",objAppointment.getDate_appointment());
            String time_appointment = JOptionPane.showInputDialog(null, "Enter new time hh:mm:ss",objAppointment.getTime_appointment());
            String reason = JOptionPane.showInputDialog(null, "Enter new reason",objAppointment.getReason());
            int id_doctor = Integer.parseInt(JOptionPane.showInputDialog(null, STR."Enter new Doctor's ID \n\{ objDoctorController.getAll(objDC.findAll())}",objAppointment.getId_doctor()));

            objAppointment.setDate_appointment(date_appointment);
            objAppointment.setTime_appointment(time_appointment);
            objAppointment.setReason(reason);
            objAppointment.setId_doctor(id_doctor);

            this.objAppointmentModel.update(objAppointment);
        }
    }

    public void delete(){
        String listAppointments = this.getAll(this.objAppointmentModel.findAll());

        int confirm;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAppointments + "Enter the ID to delete"));
        Appointment objAppointment = (Appointment) this.objAppointmentModel.findById(idDelete);

        if(objAppointment == null){
            JOptionPane.showMessageDialog(null,"Appointment not found.");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Sure? You want to delete this Appointment? \n"+objAppointment.toString(false), "Deleting Appointment",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION){
                this.objAppointmentModel.delete(objAppointment);
            }
        }
    }
}
