package controller;

import entity.Product;
import entity.Product;
import model.ProductModel;
import model.StoreModel;

import javax.swing.*;
import java.util.List;

public class ProductController {

    ProductModel objProductModel = new ProductModel();
    StoreController objSC = new StoreController();
    StoreModel objSM =  new StoreModel();

    public void search(){
        String toSearch = JOptionPane.showInputDialog(null,"Search: ");
        List resultList =  objProductModel.searchProduct(toSearch);
        StringBuilder list = new StringBuilder("Coincidences : \n");
        for(Object obj : resultList){
            Product objP = (Product) obj;
            list.append(objP.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, list.toString());

    }
    public void create(){
        Product objProduct = new Product();
        //requesting data
        String name = JOptionPane.showInputDialog(null,"Insert the name:  \n", "Adding New Product",JOptionPane.PLAIN_MESSAGE);
        Double price = Double.parseDouble(JOptionPane.showInputDialog(null,"Insert the price:  \n", "Adding New Product",JOptionPane.PLAIN_MESSAGE));
        int stock = Integer.parseInt(JOptionPane.showInputDialog(null,"Insert stock of the product: \n", "Adding New Product",JOptionPane.PLAIN_MESSAGE ));
        int id_store = Integer.parseInt(JOptionPane.showInputDialog(null,"Insert the Store's ID: \n" + objSC.read(objSM.read()), "Adding New Product" ,JOptionPane.PLAIN_MESSAGE ));

        //setting data in the object Product
        objProduct.setName(name);
        objProduct.setPrice(price);
        objProduct.setStock(stock);
        objProduct.setId_store(id_store);

        //data throw model and resigned to the object
        objProduct = (Product) objProductModel.create(objProduct);

        //finally look at me :)
        JOptionPane.showMessageDialog(null,objProduct.toString());
    }

    public void read(){
        String list = this.read(objProductModel.read());
        JOptionPane.showMessageDialog(null, list);
    }

    public String read(List listObject){
        StringBuilder list = new StringBuilder("List Products: \n");
        for(Object obj : listObject){
            Product objP = (Product) obj;
            list.append(objP.toString()).append("\n");
        }
        return list.toString();
    }

    public void update(){
        String listAuthors = this.read(this.objProductModel.read());

        int idUpdated = Integer.parseInt(JOptionPane.showInputDialog(listAuthors + "\n Enter the ID to edit."));

        Product objProduct = (Product) this.objProductModel.findById(idUpdated);

        if(objProduct == null){
            JOptionPane.showMessageDialog(null,"Product not found.");
        }else{

            String name = JOptionPane.showInputDialog(null,"Enter new name: ",objProduct.getName());
            Double price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the new price:",objProduct.getPrice()));
            int stock = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the NEW stock to ADD:" + "\nACTUAL STOCK: "+objProduct.getStock() ));

            objProduct.setName(name);
            objProduct.setPrice(price);
            objProduct.setStock(stock + objProduct.getStock());
            objProduct.setId_store(objProduct.getId_store());

            this.objProductModel.update(objProduct);
        }

    }

    public void delete(){
        String listAuthorString = this.read(this.objProductModel.read());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAuthorString + "Enter the ID to delete"));
        Product objProduct = (Product) this.objProductModel.findById(idDelete);

        if(objProduct == null){
            JOptionPane.showMessageDialog(null,"Product not found.");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete? \n" + objProduct.toString());
            if (confirm == 0){
                this.objProductModel.delete(objProduct);
            }
        }
    }
}
