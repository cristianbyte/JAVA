package controller;


import entity.Appointment;
import model.AppointmentModel;

import javax.swing.*;
import java.util.List;

public class AppointmentController {

    AppointmentModel objAppointmentModel = new AppointmentModel();

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
}
