package model;

import database.CRUD;
import database.ConfigDB;
import entity.Product;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductModel implements CRUD {

    public List searchProduct(String nameLike){
        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Initialize a list to save data.
        List<Object> listProducts = new ArrayList<>();

        try{
            //3. Write sentence:
            String sql = "SELECT * FROM products WHERE products.name LIKE ? ORDER BY products.id ASC;";
            //4. Use preparedStatement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            objPreparedStatement.setString(1, nameLike+'%');
            //5. Execute
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6. Get results
            while(objResult.next()){
                Product objProduct = new Product();
                objProduct.setId(objResult.getInt("id"));
                objProduct.setName(objResult.getString("name"));
                objProduct.setPrice(objResult.getDouble("price"));
                objProduct.setStock(objResult.getInt("stock"));
                objProduct.setId_store(objResult.getInt("id_store"));

                listProducts.add(objProduct);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }

        //7. Close connection
        ConfigDB.closeConnection();

        return listProducts;
    }
    @Override
    public Object create(Object object) {
        //1: open connection
        Connection objConnection = ConfigDB.openConnection();
        //2: create class of the object to manipulate the information requested
        Product product = (Product) object;

        try {
            //3: make the sentence in sql code
            String sql = "INSERT INTO products (name,price,stock,id_store) VALUES (?,?,?,?);";
            //4: prepare the statement to insert the values and terminate the request
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            //5: set the values in the statement
            objPrepare.setString(1, product.getName());
            objPrepare.setDouble(2, product.getPrice());
            objPrepare.setInt(3, product.getStock());
            objPrepare.setInt(4, product.getId_store());

            //6: execute the request
            objPrepare.execute();

            //7: get the key, because u don't have it yet.
            ResultSet objResult = objPrepare.getGeneratedKeys();

            //8: get the result iterating over the object
            while (objResult.next()) {
                product.setId(objResult.getInt(1));
            }
            //9: close the statement
            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Success!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: 0004" + e.getMessage());
        }
        //10: close the connection please be a good man. :)
        ConfigDB.closeConnection();
        return product;
    }

    @Override
    public List read() {
        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Initialize a list to save data.
        List<Object> listProducts = new ArrayList<>();

        try{
            //3. Write sentence:
            String sql = "SELECT * FROM products ORDER BY products.id ASC;";
            //4. Use preparedStatement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5. Execute
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6. Get results
            while(objResult.next()){
                Product objProduct = new Product();
                objProduct.setId(objResult.getInt("id"));
                objProduct.setName(objResult.getString("name"));
                objProduct.setPrice(objResult.getDouble("price"));
                objProduct.setStock(objResult.getInt("stock"));
                objProduct.setId_store(objResult.getInt("id_store"));

                listProducts.add(objProduct);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }

        //7. Close connection
        ConfigDB.closeConnection();

        return listProducts;
    }

    @Override
    public boolean update(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Product objProduct = (Product)object;
        boolean isUpdated = false;
        try{
            String sql = "UPDATE products SET name = ?, price = ?, stock = ?, id_store = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1,objProduct.getName());
            objPrepared.setDouble(2,objProduct.getPrice());
            objPrepared.setInt(3,objProduct.getStock());
            objPrepared.setInt(4,objProduct.getId_store());
            objPrepared.setInt(5,objProduct.getId());

            int rowAffected = objPrepared.executeUpdate();
            if(rowAffected>0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Successful update ");
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"ERR 0006: "+ e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }

    public boolean updateQuantity(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Product objProduct = (Product) object;
        boolean isUpdated = false;
        try{
            String sql = "UPDATE products SET stock = ? WHERE id = ?;";
            PreparedStatement objPrepared = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepared.setInt(1,objProduct.getStock());
            objPrepared.setInt(2,objProduct.getId());

            int rowAffected = objPrepared.executeUpdate();
            if(rowAffected>0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Successful update ");
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"ERR 0006: "+ e.getMessage());
        }

        ConfigDB.closeConnection();
        return isUpdated;
    }


    @Override
    public boolean delete(Object object) {
        //!1 Convert object to entity.
        Product objAuthor = (Product) object;
        //2. Elimination state
        boolean isDeleted = false;
        //3.Open connection
        Connection objConnection = ConfigDB.openConnection();

        try {
            //4. Write sentence
            String sql = "DELETE FROM products WHERE products.id = ?;";
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
        Product objProduct = null;
        try{
            //2. SQL Sentence
            String sql = "SELECT * FROM products INNER JOIN stores ON products.id_store = stores.id WHERE products.id = ?;";
            //3. Preparing statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            //4.
            objPrepare.setInt(1,id);
            //5. execute
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                objProduct = new Product();
                objProduct.setId(objResult.getInt("id"));
                objProduct.setName(objResult.getString("name"));
                objProduct.setPrice(objResult.getDouble("price"));
                objProduct.setStock(objResult.getInt("stock"));
                objProduct.setId_store(objResult.getInt("id_store"));
                objProduct.setName_store(objResult.getString("stores.name"));
            }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return objProduct;
    }
}
