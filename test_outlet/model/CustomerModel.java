package model;

import database.CRUD;
import database.ConfigDB;
import entity.Customer;
import entity.Product;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel implements CRUD {

    public List search(String nameLike){
        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Initialize a list to save data.
        List<Object> listCustomers = new ArrayList<>();

        try{
            //3. Write sentence:
            String sql = "SELECT * FROM customers WHERE customers.name LIKE ? ORDER BY customers.id ASC;";
            //4. Use preparedStatement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, nameLike+'%');
            //5. Execute
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6. Get results
            while(objResult.next()){
                Customer objC = new Customer();
                objC.setId(objResult.getInt("id"));
                objC.setName(objResult.getString("name"));
                objC.setLast_name(objResult.getString("last_name"));
                objC.setEmail(objResult.getString("email"));

                listCustomers.add(objC);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }

        //7. Close connection
        ConfigDB.closeConnection();

        return listCustomers;
    }
    @Override
    public Object create(Object object) {
        //1: open connection
        Connection objConnection = ConfigDB.openConnection();
        //2: create class of the object to manipulate the information requested
        Customer customer = (Customer) object;

        try {
            //3: make the sentence in sql code
            String sql = "INSERT INTO customers (name,last_name,email) VALUES (?,?,?);";
            //4: prepare the statement to insert the values and terminate the request
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //5: set the values in the statement
            objPrepare.setString(1, customer.getName());
            objPrepare.setString(2, customer.getLast_name());
            objPrepare.setString(3, customer.getEmail());

            //6: execute the request
            objPrepare.execute();

            //7: get the key, because u don't have it yet.
            ResultSet objResult = objPrepare.getGeneratedKeys();

            //8: get the result iterating over the object
            while (objResult.next()) {
                customer.setId(objResult.getInt(1));
            }
            //9: close the statement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Success!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: 0001" + e.getMessage());
        }
        //10: close the connection please be a good man. :)
        ConfigDB.closeConnection();
        return customer;
    }

    @Override
    public List read() {
        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Initialize a list to save data.
        List<Object> listCustomers = new ArrayList<>();

        try{
            //3. Write sentence:
            String sql = "SELECT * FROM customers ORDER BY customers.id ASC;";
            //4. Use preparedStatement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5. Execute
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6. Get results
            while(objResult.next()){
                Customer objCustomer = new Customer();
                objCustomer.setId(objResult.getInt("id"));
                objCustomer.setName(objResult.getString("name"));
                objCustomer.setLast_name(objResult.getString("last_name"));
                objCustomer.setEmail(objResult.getString("email"));

                listCustomers.add(objCustomer);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }

        //7. Close connection
        ConfigDB.closeConnection();

        return listCustomers;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Customer objCustomer = (Customer)object;
        boolean isUpdated = false;
        try{
            String sql = "UPDATE customers SET name = ?, last_name = ?, email = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1,objCustomer.getName());
            objPrepared.setString(2,objCustomer.getLast_name());
            objPrepared.setString(3,objCustomer.getEmail());
            objPrepared.setInt(4,objCustomer.getId());

            int rowAffected = objPrepared.executeUpdate();
            if(rowAffected>0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Successful update ");
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"ERR 0002: "+ e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }


    @Override
    public boolean delete(Object object) {
        //!1 Convert object to entity.
        Customer objAuthor = (Customer) object;
        //2. Elimination state
        boolean isDeleted = false;
        //3.Open connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Write sentence
            String sql = "DELETE FROM customers WHERE customers.id = ?;";
            //5.
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //6.
            objPrepare.setInt(1, objAuthor.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows>0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The delete was successful");
            }

            ConfigDB.closeConnection();
            return isDeleted;

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error 0003: " + e.getMessage());
        }

        return isDeleted;

    }

    @Override
    public Object findById(int id) {
        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        Customer objCustomer = null;
        try{
            //2. SQL Sentence
            String sql = "SELECT * FROM customers WHERE customers.id = ?;";
            //3. Preparing statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //4.
            objPrepare.setInt(1,id);
            //5. execute
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                objCustomer = new Customer();
                objCustomer.setId(objResult.getInt("id"));
                objCustomer.setName(objResult.getString("name"));
                objCustomer.setLast_name(objResult.getString("last_name"));
                objCustomer.setEmail(objResult.getString("email"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objCustomer;
    }
}
