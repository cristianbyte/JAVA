package controller;

import entity.Product;
import entity.Store;
import model.StoreModel;

import javax.swing.*;
import java.util.List;

public class StoreController {
    StoreModel objStoreModel = new StoreModel();
    public void read(){
        String list = this.read(objStoreModel.read());
        JOptionPane.showMessageDialog(null, list);
    }

    public String read(List listObject){
        StringBuilder list = new StringBuilder("List Stores: \n");
        for(Object obj : listObject){
            Store objStore = (Store) obj;

            list.append(objStore.toString()).append("\n");
        }
        return list.toString();
    }
}
