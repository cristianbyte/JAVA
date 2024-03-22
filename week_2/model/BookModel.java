package model;

import database.CRUDBook;
import database.ConfigDB;
import entity.Author;
import entity.Book;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookModel implements CRUDBook {

    Author objAuthor = new Author();

    @Override
    public Object create(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Book book = (Book) object;

        try {
            String sql = "INSERT INTO books (title,publication_year,price,idAuthor) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, book.getTitle());
            objPrepare.setInt(2, book.getPublication_year());
            objPrepare.setDouble(3, book.getPrice());
            objPrepare.setInt(4, book.getIdAuthor());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                book.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Success!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return book;
    }

    @Override
    public Object query(int id) {
        return null;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = (Book)object;
        boolean isUpdated = false;
/*
        this.id = id;
        this.title = title;
        this.publication_year = title;
        this.price = price;
        this.idAuthor = idAuthor;
        this.objAuthor = objAuthor;
        */
        try{
            String sql = "UPDATE books SET title = ?, publication_year = ?, price = ?, idAuthor = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1,objBook.getTitle());
            objPrepared.setInt(2,objBook.getPublication_year());
            objPrepared.setDouble(3,objBook.getPrice());
            objPrepared.setInt(4,objBook.getIdAuthor());
            objPrepared.setInt(5,objBook.getId());

            int rowAffected = objPrepared.executeUpdate();
            if(rowAffected>0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Successful update ");
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"ERR 23452: "+ e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object object) {
        //!1 Convert object to entity.
        Book objBook = (Book) object;
        //2. Elimination state
        boolean isDeleted = false;
        //3.Open connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Write sentence
            String sql = "DELETE FROM books WHERE books.id = ?;";
            //5.
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //6.
            objPrepare.setInt(1, objBook.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The delete was successful");
            }

            ConfigDB.closeConnection();
            return isDeleted;

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error 2528: " + e.getMessage());
        }

        return isDeleted;
    }

    @Override
    public List<Object> findAll() {
        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Initialize a list to save data.
        List<Object> listBooks = new ArrayList<>();



        try{
            //3. Write sentence:
            String sql = "SELECT * FROM books INNER JOIN authors ON books.idAuthor = authors.id ORDER BY books.id ASC;";
            //4. Use preparedStatement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5. Execute
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6. Get results

            /*
        this.id = id;
        this.title = title;
        this.publication_year = title;
        this.price = price;
        this.idAuthor = idAuthor;
        this.objAuthor = objAuthor;
        */
            while(objResult.next()){
                Book objBook = new Book();
                Author objAuthor = new Author();
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setPublication_year(objResult.getInt("publication_year"));
                objBook.setPrice(objResult.getDouble("price"));
                objBook.setIdAuthor(objResult.getInt("idAuthor"));
                objAuthor.setName(objResult.getString("name"));

                objBook.setObjAuthor(objAuthor);

                listBooks.add(objBook);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }

        //7. Close connection
        ConfigDB.closeConnection();

        return listBooks;
    }

    @Override
    public List<Object> findAll(String idAuthor) {
        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Initialize a list to save data.
        List<Object> listBooks = new ArrayList<>();

        try{
            //3. Write sentence:
            String sql = idAuthor.isEmpty() ? "SELECT * FROM books INNER JOIN authors ON books.idAuthor = authors.id ORDER BY books.id ASC;" : "SELECT * FROM books INNER JOIN authors ON books.idAuthor = authors.id WHERE books.idAuthor = "+idAuthor+" ORDER BY books.id ASC" ;
            //4. Use preparedStatement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5. Execute
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6. Get results

            while(objResult.next()){
                Book objBook = new Book();
                Author objAuthore = new Author();
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setPublication_year(objResult.getInt("publication_year"));
                objBook.setPrice(objResult.getDouble("price"));
                objBook.setIdAuthor(objResult.getInt("idAuthor"));
                objAuthore.setName(objResult.getString("name"));
                objBook.setObjAuthor(objAuthore);

                listBooks.add(objBook);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }

        //7. Close connection
        ConfigDB.closeConnection();

        return listBooks;
    }

    @Override
    public Object findById(int id) {
        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = null;
        try{
            //2. SQL Sentence
            String sql = "SELECT * FROM books INNER JOIN authors ON books.idAuthor = authors.id WHERE books.id = ?";
            //3. Preparing statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //4.
            objPrepare.setInt(1,id);
            //5. execute
            ResultSet objResult = objPrepare.executeQuery();
            /*
        this.id = id;
        this.title = title;
        this.publication_year = title;
        this.price = price;
        this.idAuthor = idAuthor;
        this.objAuthor = objAuthor;
        */
            while (objResult.next()){
                objBook = new Book();
                objBook.setId(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setPublication_year(objResult.getInt("publication_year"));
                objBook.setPrice(objResult.getDouble("price"));
                objBook.setIdAuthor(objResult.getInt("idAuthor"));
                objAuthor.setId(objResult.getInt("id"));
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNationality(objResult.getString("nationality"));

                objBook.setObjAuthor(objAuthor);
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objBook;
    }
}
