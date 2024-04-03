package controller;

import entity.Specialty;
import model.SpecialtyModel;

import javax.swing.*;
import java.util.List;
/*CREATE TABLE specialties(
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
name varchar(40) NOT NULL,
description varchar(40) NOT NULL
);*/
public class SpecialtyController {
    public SpecialtyModel objSpecialtyModel = new SpecialtyModel();

    private String creatingQuestion( String message,String title){
        return JOptionPane.showInputDialog(null,
                message,
                title,
                JOptionPane.QUESTION_MESSAGE
        );
    }

    public void create() {

        Specialty objSpecialty = new Specialty();

        String name = creatingQuestion("Name: ","Creating Specialty");
        objSpecialty.setName(name);
        String description = creatingQuestion("Description: ","Creating Specialty");
        objSpecialty.setDescription(description);

        objSpecialty = (Specialty) this.objSpecialtyModel.create(objSpecialty);

        JOptionPane.showMessageDialog(null, objSpecialty.toString());
    }

    public void getAll(){
        String list = this.getAll(objSpecialtyModel.findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public String getAll(List listObject){
        StringBuilder list = new StringBuilder("List Specialties: \n");
        for(Object obj : listObject){
            Specialty objCoder = (Specialty) obj;
            list.append(objCoder.toString()).append("\n");
        }
        return list.toString();
    }

    public void update(){
        String listSpecialty = this.getAll(this.objSpecialtyModel.findAll());

        int idUpdated = Integer.parseInt(JOptionPane.showInputDialog(listSpecialty + "\n Enter the ID to edit."));

        Specialty objSpecialty = (Specialty) this.objSpecialtyModel.findById(idUpdated);

        if(objSpecialty == null){
            JOptionPane.showMessageDialog(null,"Specialty not found.");
        }else{

            String name = JOptionPane.showInputDialog(null,"Enter the new name:",objSpecialty.getName());
            String description = JOptionPane.showInputDialog(null, "Enter new description:",objSpecialty.getDescription());

            objSpecialty.setName(name);
            objSpecialty.setDescription(description);

            this.objSpecialtyModel.update(objSpecialty);
        }
    }

    public void delete(){
        String listSpecialty = this.getAll(this.objSpecialtyModel.findAll());

        int confirm;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listSpecialty + "Enter the ID to delete"));
        Specialty objSpecialty = (Specialty) this.objSpecialtyModel.findById(idDelete);

        if(objSpecialty == null){
            JOptionPane.showMessageDialog(null,"Specialty not found.");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Sure? You want to delete this Specialty? \n"+objSpecialty.toString(), "Deleting Specialty",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION){
                this.objSpecialtyModel.delete(objSpecialty);
            }
        }
    }
}
