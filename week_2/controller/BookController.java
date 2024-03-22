package controller;
import entity.Author;
import entity.Book;
import model.AuthorModel;
import model.BookModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BookController {

    BookModel objBookModel = new BookModel();
    AuthorModel objAuthorModel = new AuthorModel();

    public void getAll(){
        String list = this.getAll(objBookModel.findAll(""));
        JOptionPane.showMessageDialog(null, list);
    }

    public void getAll(String idAuthor){
        String list = this.getAll(objBookModel.findAll(idAuthor));
        JOptionPane.showMessageDialog(null, list);
    }

    public void searchByAuthor(){
        List list = objAuthorModel.findAll();
        String idAuthor = JOptionPane.showInputDialog(null,list +"\n"+"Inset the ID of the author to search for: " );
        this.getAll(idAuthor);
    }

    public String getAll(List listObject){
        StringBuilder list = new StringBuilder("List Books: \n");
        for(Object obj : listObject){
            Book objBook = (Book) obj;

            list.append(objBook.getId()).append(": ").append(objBook.toString()).append("\n");
        }
        return list.toString();
    }


    public void create(){
        Book objBook = new Book();
        String name = JOptionPane.showInputDialog("Insert Name: ");
        int publication_year = Integer.parseInt(JOptionPane.showInputDialog("Insert publication year: "));
        double price = Double.parseDouble(JOptionPane.showInputDialog("Insert price: "));
        int idAuthor = Integer.parseInt(JOptionPane.showInputDialog(this.objAuthorModel.findAll()+"\n Enter the ID of the author."));

        objBook.setTitle(name);
        objBook.setPublication_year(publication_year);
        objBook.setPrice(price);
        objBook.setIdAuthor(idAuthor);
        objBook.setObjAuthor((Author) this.objAuthorModel.findById(idAuthor));

        objBook = (Book) this.objBookModel.create(objBook);

        JOptionPane.showMessageDialog(null, objBook.toString());
    }

    public void update(){
        String listBooks = this.getAll(this.objBookModel.findAll(""));

        int idUpdated = Integer.parseInt(JOptionPane.showInputDialog(listBooks + "\n Enter the ID to edit."));

        Book objBook = (Book) this.objBookModel.findById(idUpdated);

        if(objBook == null){
            JOptionPane.showMessageDialog(null,"Book not found.");
        }else{
                /*
                this.id = id;
                this.title = title;
                this.publication_year = publication_year;
                this.price = price;
                this.idAuthor = idAuthor;
                this.objAuthor = objAuthor;
                */
            String name = JOptionPane.showInputDialog(null,"Enter new name: ",objBook.getTitle());
            int publication_year = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the new publication year:",objBook.getPublication_year()));
            double price = Double.parseDouble( JOptionPane.showInputDialog(null, "Enter the new price:",objBook.getPrice()));
            int idAuthor = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the new ID of the author: \n"+this.objAuthorModel.findAll() ,objBook.getIdAuthor()));

            objBook.setTitle(name);
            objBook.setPublication_year(publication_year);
            objBook.setPrice(price);
            objBook.setIdAuthor(idAuthor);

            this.objBookModel.update(objBook);
        }
    }

    public void delete(){
        String listBookString = this.getAll(this.objBookModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listBookString + "Enter the ID to delete: "));
        Book objBook = (Book) this.objBookModel.findById(idDelete);

        if(objBook == null){
            JOptionPane.showMessageDialog(null,"Author not found.");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are u sure u want to delete? \n" + objBook.toString());
            if (confirm == 0){
                this.objBookModel.delete(objBook);
            }
        }
    }
}
