package controller;

import entity.Customer;
import entity.Product;
import entity.Purchase;
import entity.Store;
import model.CustomerModel;
import model.ProductModel;
import model.PurchaseModel;
import model.StoreModel;

import javax.swing.*;
import java.util.List;

public class PurchaseController {

    PurchaseModel objPurchaseModel = new PurchaseModel();
    ProductController objPC = new ProductController();
    CustomerController objCC = new CustomerController();
    CustomerModel objCM = new CustomerModel();
    ProductModel objPM =  new ProductModel();
    StoreModel objSM =  new StoreModel();


    public void create(){
        Purchase objPurchase = new Purchase();
        //requesting data
        int id_product = Integer.parseInt(JOptionPane.showInputDialog(null,"Insert the Product's ID: \n" + objPC.read(objPM.read()), "Adding New Purchase" ,JOptionPane.PLAIN_MESSAGE ));
        Product objActualProduct = (Product) objPM.findById(id_product);
        //validating stock
        if(objActualProduct != null && objActualProduct.getStock() > 0){
            //validating amount to puchase
            int quantity = Integer.parseInt(JOptionPane.showInputDialog(null,"Insert the amount:  \n", "Adding New Purchase",JOptionPane.PLAIN_MESSAGE));
            if(quantity <= objActualProduct.getStock()){
                String purchase_date = (String) JOptionPane.showInputDialog(null,"Insert the date:  yyyy-mm-dd\n", "Adding New Purchase",JOptionPane.PLAIN_MESSAGE,null,null, "2024-04-10");


                int id_store = objActualProduct.getId();
                String name_product = objActualProduct.getName();
                Double price_product = objActualProduct.getPrice();
                String name_store = objActualProduct.getName_store();

                int id_client = Integer.parseInt(JOptionPane.showInputDialog(null,"Insert the Client's ID: \n" + objCC.read(objCM.read()), "Adding New Purchase" ,JOptionPane.PLAIN_MESSAGE ));
                Customer objActualCustomer = (Customer) objCM.findById(id_client);

                //setting data in the object Purchase
                objPurchase.setId_product(id_product);
                objPurchase.setName_product(name_product);

                //amount logic for products
                objPurchase.setQuantity(quantity);
                objActualProduct.setStock(objActualProduct.getStock()-quantity);
                objPM.updateQuantity(objActualProduct);


                objPurchase.setPurchase_date(purchase_date);
                objPurchase.setId_customer(id_client);
                objPurchase.setName_customer(objActualCustomer.getName()+ ' ' + objActualCustomer.getLast_name());
                objPurchase.setEmail_customer(objActualCustomer.getEmail());
                objPurchase.setName_store(name_store);
                objPurchase.setPrice_product(price_product);

                //data throw model and resigned to the object
                objPurchase = (Purchase) objPurchaseModel.create(objPurchase);



                //finally look at me :)
                JOptionPane.showMessageDialog(null,objPurchase.bill());

            }else{
                JOptionPane.showMessageDialog(null,"OUT OF STOCK, redirecting...");
            }
        }else{
            JOptionPane.showMessageDialog(null,"OUT OF STOCK OR PRODUCT DON'T MATCH");
        }
    }

    public void read(){
        String list = this.read(objPurchaseModel.read());
        JOptionPane.showMessageDialog(null, list);
    }

    public String read(List listObject){
        StringBuilder list = new StringBuilder("List Purchases: \n");
        for(Object obj : listObject){
            Purchase objCoder = (Purchase) obj;

            list.append(objCoder.toString(true)).append("\n");
        }
        return list.toString();
    }

    public void update(){
        String listAuthors = this.read(this.objPurchaseModel.read());

        int idUpdated = Integer.parseInt(JOptionPane.showInputDialog(listAuthors + "\n Enter the ID to edit."));

        Purchase objPurchase = (Purchase) this.objPurchaseModel.findById(idUpdated);

        if(objPurchase == null){
            JOptionPane.showMessageDialog(null,"Purchase not found.");
        }else{
            ProductModel objPM = new ProductModel();
            Product objActualProduct = (Product) objPM.findById(objPurchase.getId_product());

            int actualStock = objActualProduct.getStock();
            int edit = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the NEW stock to ADD or REMOVE with (-):" + "\n ACTUAL AMOUNT: " + objPurchase.getQuantity()));
            if(edit <= actualStock && objPurchase.getQuantity() + edit > 0){
                objPurchase.setQuantity(objPurchase.getQuantity() + edit);

                objActualProduct.setStock(actualStock+edit);
                objPM.updateQuantity(objActualProduct);
                this.objPurchaseModel.update(objPurchase);
            }else{

                JOptionPane.showMessageDialog(null,"OUT OF STOCK or THE AMOUNT CAN'T BE LESS THAN 0");
            }

        }

    }

    public void delete(){
        String listAuthorString = this.read(this.objPurchaseModel.read());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAuthorString + "Enter the ID to delete"));
        Purchase objPurchase = (Purchase) this.objPurchaseModel.findById(idDelete);

        if(objPurchase == null){
            JOptionPane.showMessageDialog(null,"Purchase not found.");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete? \n" + objPurchase.toString());
            if (confirm == 0){
                this.objPurchaseModel.delete(objPurchase);
            }
        }
    }
}
