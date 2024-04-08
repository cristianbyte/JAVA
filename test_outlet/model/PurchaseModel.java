package model;

import database.CRUD;
import database.ConfigDB;
import entity.Purchase;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseModel implements CRUD {
    @Override
    public Object create(Object object) {
        //1: open connection
        Connection objConnection = ConfigDB.openConnection();
        //2: create class of the object to manipulate the information requested
        Purchase purchase = (Purchase) object;

        try {
            //3: make the sentence in sql code
            String sql = "INSERT INTO purchases (purchase_date,quantity,id_customer,id_product) VALUES (?,?,?,?);";
            //4: prepare the statement to insert the values and terminate the request
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //5: set the values in the statement
            objPrepare.setString(1, purchase.getPurchase_date());
            objPrepare.setInt(2, purchase.getQuantity());
            objPrepare.setInt(3, purchase.getId_customer());
            objPrepare.setInt(4, purchase.getId_product());

            //6: execute the request
            objPrepare.execute();

            //7: get the key, because u don't have it yet.
            ResultSet objResult = objPrepare.getGeneratedKeys();

            //8: get the result iterating over the object
            while (objResult.next()) {
                purchase.setId(objResult.getInt(1));
            }
            //9: close the statement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Success!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: 0009" + e.getMessage());
        }
        //10: close the connection please be a good man. :)
        ConfigDB.closeConnection();
        return purchase;
    }

    @Override
    public List read() {
        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Initialize a list to save data.
        List<Object> listPurchases = new ArrayList<>();

        try{
            //3. Write sentence:
            String sql = "SELECT * FROM purchases INNER JOIN customers ON purchases.id_customer = customers.id INNER JOIN products ON purchases.id_product = products.id;";
            //4. Use preparedStatement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5. Execute
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6. Get results
            while(objResult.next()){
                Purchase objPurchase = new Purchase();
                objPurchase.setId(objResult.getInt("id"));
                objPurchase.setPurchase_date(objResult.getString("purchase_date"));
                objPurchase.setQuantity(objResult.getInt("quantity"));
                objPurchase.setId_customer(objResult.getInt("id_customer"));
                objPurchase.setId_product(objResult.getInt("id_product"));
                objPurchase.setName_customer(objResult.getString("customers.name"));
                objPurchase.setName_product(objResult.getString("products.name"));

                listPurchases.add(objPurchase);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }

        //7. Close connection
        ConfigDB.closeConnection();

        return listPurchases;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Purchase objPurchase = (Purchase)object;
        boolean isUpdated = false;
        try{
            String sql = "UPDATE purchases SET purchase_date = ?, quantity = ?, id_customer = ?, id_product = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1,objPurchase.getPurchase_date());
            objPrepared.setInt(2,objPurchase.getQuantity());
            objPrepared.setInt(3,objPurchase.getId_customer());
            objPrepared.setInt(4,objPurchase.getId_product());
            objPrepared.setInt(5,objPurchase.getId());

            int rowAffected = objPrepared.executeUpdate();
            if(rowAffected>0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Successful update ");
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"ERR 0010: "+ e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object object) {
        //!1 Convert object to entity.
        Purchase objAuthor = (Purchase) object;
        //2. Elimination state
        boolean isDeleted = false;
        //3.Open connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Write sentence
            String sql = "DELETE FROM purchases WHERE purchases.id = ?;";
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
            JOptionPane.showMessageDialog(null, "Error 0007: " + e.getMessage());
        }

        return isDeleted;

    }

    @Override
    public Object findById(int id) {
        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        Purchase objPurchase = null;
        try{
            //2. SQL Sentence
            String sql = "SELECT * FROM purchases INNER JOIN customers ON purchases.id_customer = customers.id INNER JOIN products ON purchases.id_product = products.id WHERE purchases.id = ?;";
            //3. Preparing statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //4.
            objPrepare.setInt(1,id);
            //5. execute
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                objPurchase = new Purchase();
                objPurchase.setId(objResult.getInt("id"));
                objPurchase.setPurchase_date(objResult.getString("purchase_date"));
                objPurchase.setQuantity(objResult.getInt("quantity"));
                objPurchase.setId_customer(objResult.getInt("id_customer"));
                objPurchase.setName_customer(objResult.getString("customers.name"));
                objPurchase.setId_product(objResult.getInt("id_product"));
                objPurchase.setName_product(objResult.getString("products.name"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objPurchase;
    }
}
