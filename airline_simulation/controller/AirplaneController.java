package controller;

import entity.Airplane;
import model.AirplaneModel;

import javax.swing.*;
import java.util.List;

import static java.lang.StringTemplate.STR;
/*
CREATE TABLE airplanes(
        id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
        model varchar(40) NOT NULL,
capacity INT NOT NULL
);*/

public class AirplaneController {

    public AirplaneModel objAirplaneModel = new AirplaneModel();

    private String creatingQuestion( String message,String title){
        return JOptionPane.showInputDialog(null,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE
        );
    }

    public void create() {

        Airplane objAirplane = new Airplane();

        String name = creatingQuestion("Name: ","Creating Airplane");
        objAirplane.setModel(name);
        int description = Integer.parseInt(creatingQuestion("Capacity: ","Creating Airplane"));
        objAirplane.setCapacity(description);

        objAirplane = (Airplane) this.objAirplaneModel.create(objAirplane);

        JOptionPane.showMessageDialog(null, objAirplane.toString());
    }

    public void getAll(){
        String list = this.getAll(objAirplaneModel.findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public String getAll(List listObject){
        StringBuilder list = new StringBuilder("List Airplanes: \n");
        for(Object obj : listObject){
            Airplane objAir = (Airplane) obj;
            list.append(objAir.toString()).append("\n");
        }
        return list.toString();
    }

    public void delete(){
        String listAirplane = this.getAll(this.objAirplaneModel.findAll());

        int confirm;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(null,listAirplane + "Enter the ID to delete:"));
        Airplane objAirplane = (Airplane) this.objAirplaneModel.findById(idDelete);

        if(objAirplane == null){
            JOptionPane.showMessageDialog(null,"Airplane not found.");
        }else{
            confirm = JOptionPane.showConfirmDialog(null, "Sure? You want to delete this Airplane? \n" + objAirplane.toString(), "Deleting Airplane",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION){
                this.objAirplaneModel.delete(objAirplane);
            }
        }
    }
}
