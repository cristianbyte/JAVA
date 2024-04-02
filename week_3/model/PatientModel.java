package model;

import database.CRUD;
import database.ConfigDB;
import entity.Patient;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class PatientModel implements CRUD {
    @Override
    public Object create(Object object) {
        Connection objConnection = ConfigDB.openConnection();
        Patient patient = (Patient) object;

        /*CREATE TABLE patients(
        id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
        name varchar(40) NOT NULL,
        last_name varchar(40) NOT NULL,
        birth_date DATE NOT NULL,
        identity varchar(40) NOT NULL
        );*/

        try {
            String sql = "INSERT INTO patients (name,last_name,birth_date,identity) VALUES (?,?,?,?);";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepare.setString(1, patient.getName());
            objPrepare.setString(2, patient.getLast_name());
            objPrepare.setString(3, patient.getBirth_date());
            objPrepare.setString(4, patient.getIdentity());

            objPrepare.execute();

            ResultSet objResult = objPrepare.getGeneratedKeys();

            while (objResult.next()) {
                patient.setId(objResult.getInt(1));
            }

            objPrepare.close();
            JOptionPane.showMessageDialog(null, "Success!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error  3546: " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return  patient;
    }

    @Override
    public List<Object> read(Object object) {
        return null;
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
    public Object find(int id) {
        return null;
    }
}
