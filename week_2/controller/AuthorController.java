package controller;

import entity.Author;
import model.AuthorModel;

import javax.swing.*;
import java.util.List;

public class AuthorController {

    AuthorModel objAuthorModel;
    public AuthorController(){
        this.objAuthorModel = new AuthorModel();
    }

    public void create(){
        Author objAuthor = new Author();
        String name = JOptionPane.showInputDialog("Insert Name: ");
        String nationality = JOptionPane.showInputDialog("Insert Nationality: ");

        objAuthor.setName(name);
        objAuthor.setNationality(nationality);

        objAuthor = (Author) this.objAuthorModel.create(objAuthor);

        JOptionPane.showMessageDialog(null, objAuthor.toString());
    }

    public void getAll(){
        String list = this.getAll(objAuthorModel.findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public String getAll(List listObject){
        StringBuilder list = new StringBuilder("List Authors: \n");
        for(Object obj : listObject){
            Author objCoder = (Author) obj;

            list.append(objCoder.toString()).append("\n");
        }
        return list.toString();
    }

    public void update(){
        String listAuthors = this.getAll(this.objAuthorModel.findAll());

        int idUpdated = Integer.parseInt(JOptionPane.showInputDialog(listAuthors + "\n Enter the ID to edit."));

        Author objAuthor = (Author) this.objAuthorModel.findById(idUpdated);

        if(objAuthor == null){
            JOptionPane.showMessageDialog(null,"Author not found.");
        }else{
            String name = JOptionPane.showInputDialog(null,"Enter new name: ",objAuthor.getName());
            String nationality = JOptionPane.showInputDialog(null, "Enter the new nationality:",objAuthor.getNationality());

            objAuthor.setName(name);
            objAuthor.setNationality(nationality);

            this.objAuthorModel.update(objAuthor);
        }

    }

    public void delete(){
        String listAuthorString = this.getAll(this.objAuthorModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAuthorString + "Enter the ID to delete"));
        Author objAuthor = (Author) this.objAuthorModel.findById(idDelete);

        if(objAuthor == null){
            JOptionPane.showMessageDialog(null,"Author not found.");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are u sure u want to delete? \n" + objAuthor.toString());
            if (confirm == 0){
                this.objAuthorModel.delete(objAuthor);
            }
        }
    }
}
