import controller.CustomerController;
import controller.ProductController;
import controller.PurchaseController;
import controller.StoreController;
import database.ConfigDB;

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        CustomerController objCustomerController = new CustomerController();
        ProductController objProductController = new ProductController();
        StoreController objStoreController = new StoreController();
        PurchaseController objPurchaseController = new PurchaseController();

        while (true) {
            int selected = 0;
            String selection = JOptionPane.showInputDialog(null,"Welcome to EdnaMode Mall\n" +
                    "\n1: Manage Customers" +
                    "\n2: Manage Products" +
                    "\n3: Manage Purchases" +
                    "\n4: View Stores" +
                    "\n5: Exit or CANCEL");
            if (selection != null){try{selected = Integer.parseInt(selection);}catch (Exception e){JOptionPane.showMessageDialog(null,"Invalid, bye");break;}}

            if( selected == 1){
                while(true){
                    String customerSelection = JOptionPane.showInputDialog(null,"Admin Customer: \n" +
                            "\n1: View All Customers" +
                            "\n2: Add new Customer" +
                            "\n3: Update Customer" +
                            "\n4: Delete Customer" +
                            "\n5: Search Customers");
                    if (selection != null){
                        try{selected = Integer.parseInt(customerSelection);}catch (Exception e){JOptionPane.showMessageDialog(null,"Invalid, redirecting...");break;}}
                    if(selected == 1){
                        objCustomerController.read();
                    }else if(selected == 2 ){
                        objCustomerController.create();
                    }else if(selected == 3){
                        objCustomerController.update();
                    }else if(selected == 4){
                        objCustomerController.delete();
                    } else if(selected == 5){
                        objCustomerController.search();
                    } else{
                        break;
                    }
                }

            } else if (selected == 2) {
                while(true){
                    String productSelection = JOptionPane.showInputDialog(null,"Admin Products: \n" +
                            "\n1: View All Products" +
                            "\n2: Add new Product" +
                            "\n3: Update Product" +
                            "\n4: Delete Product" +
                            "\n5: Search Product");
                    if (selection != null){
                        try{selected = Integer.parseInt(productSelection);}catch (Exception e){JOptionPane.showMessageDialog(null,"Invalid, redirecting...");break;}}
                    if(selected == 1){
                        objProductController.read();
                    }else if(selected == 2 ){
                        objProductController.create();
                    }else if(selected == 3){
                        objProductController.update();
                    }else if(selected == 4){
                        objProductController.delete();
                    } else if(selected == 5){
                        objProductController.search();
                    }else {
                        break;
                    }
                }

            } else if (selected == 3) {
                while(true){
                    String purchaseSelection = JOptionPane.showInputDialog(null,"Admin Purchases: \n" +
                            "\n1: View All Purchases" +
                            "\n2: Add new Purchases" +
                            "\n3: Update Purchases" +
                            "\n4: Delete Purchases");
                    if (selection != null){
                        try{selected = Integer.parseInt(purchaseSelection);}catch (Exception e){JOptionPane.showMessageDialog(null,"Invalid, redirecting...");break;}}
                    if(selected == 1){
                        objPurchaseController.read();
                    }else if(selected == 2 ){
                        objPurchaseController.create();
                    }else if(selected == 3){
                        objPurchaseController.update();
                    }else if(selected == 4){
                        objPurchaseController.delete();
                    } else{
                        break;
                    }
                }

            } else if (selected == 4) {
                objStoreController.read();

            } else if (selected == 5) {
                break;
            } else{
                JOptionPane.showMessageDialog(null, "Incorrect option! Bye.");
                break;
            }
        }
    }
}