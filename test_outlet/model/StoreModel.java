package model;

import database.CRUD;
import database.ConfigDB;
import entity.Store;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreModel implements CRUD {
    @Override
    public Object create(Object object) {
        return null;
    }

    @Override
    public List read() {
        //1. Open connection
        Connection objConnection = ConfigDB.openConnection();
        //2. Initialize a list to save data.
        List<Object> listStores = new ArrayList<>();

        try{
            //3. Write sentence:
            String sql = "SELECT * FROM stores ORDER BY stores.id ASC;";
            //4. Use preparedStatement
            PreparedStatement objPreparedStatement = objConnection.prepareStatement(sql);
            //5. Execute
            ResultSet objResult = objPreparedStatement.executeQuery();
            //6. Get results
            while(objResult.next()){
                Store objStore = new Store();
                objStore.setId(objResult.getInt("id"));
                objStore.setName(objResult.getString("name"));
                objStore.setLocation(objResult.getString("location"));

                listStores.add(objStore);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data acquisition error");
        }

        //7. Close connection
        ConfigDB.closeConnection();

        return listStores;
    }

    @Override
    public boolean update(Object object) {
        return false;
    }

    @Override
    public boolean delete(Object object) {
        return false;
    }

    @Override
    public Object findById(int id) {
        return null;
    }
}
