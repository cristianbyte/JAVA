package controller;

import entity.Customer;
import entity.Product;
import model.CustomerModel;

import javax.swing.*;
import java.util.List;

public class CustomerController {

    CustomerModel objCustomerModel = new CustomerModel();

    public void search(){
        String toSearch = JOptionPane.showInputDialog(null,"Search: ");
        List resultList =  objCustomerModel.search(toSearch);
        StringBuilder list = new StringBuilder("Coincidences : \n");
        for(Object obj : resultList){
            Customer objP = (Customer) obj;
            list.append(objP.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, list.toString());
    }
    public void create(){
        Customer objCustomer = new Customer();
        //requesting data
        String name = JOptionPane.showInputDialog(null,"Insert the name:  \n", "Adding New Customer",JOptionPane.PLAIN_MESSAGE);
        String last_name = JOptionPane.showInputDialog(null,"Insert the last name:  \n", "Adding New Customer",JOptionPane.PLAIN_MESSAGE);
        String email = (String) JOptionPane.showInputDialog(null,"Insert the email: \n", "Adding New Customer",JOptionPane.PLAIN_MESSAGE,null, null, "example@ednamode.com");

        //setting data in the object customer
        objCustomer.setName(name);
        objCustomer.setLast_name(last_name);
        objCustomer.setEmail(email);

        //data throw model and resigned to the object
        objCustomer = (Customer) objCustomerModel.create(objCustomer);

        //finally look at me :)
        JOptionPane.showMessageDialog(null,objCustomer.toString());
    }

    public void read(){
        String list = this.read(objCustomerModel.read());
        JOptionPane.showMessageDialog(null, list);
    }

    public String read(List listObject){
        StringBuilder list = new StringBuilder("List Customers: \n");
        for(Object obj : listObject){
            Customer objCoder = (Customer) obj;

            list.append(objCoder.toString()).append("\n");
        }
        return list.toString();
    }

    public void update(){
        String listAuthors = this.read(this.objCustomerModel.read());

        int idUpdated = Integer.parseInt(JOptionPane.showInputDialog(listAuthors + "\n Enter the ID to edit."));

        Customer objCustomer = (Customer) this.objCustomerModel.findById(idUpdated);

        if(objCustomer == null){
            JOptionPane.showMessageDialog(null,"Customer not found.");
        }else{
            String name = JOptionPane.showInputDialog(null,"Enter new name: ",objCustomer.getName());
            String last_name = JOptionPane.showInputDialog(null, "Enter the new last name:",objCustomer.getLast_name());
            String email = JOptionPane.showInputDialog(null, "Enter the new email:",objCustomer.getEmail());

            objCustomer.setName(name);
            objCustomer.setLast_name(last_name);
            objCustomer.setEmail(email);

            this.objCustomerModel.update(objCustomer);
        }

    }

    public void delete(){
        String listAuthorString = this.read(this.objCustomerModel.read());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAuthorString + "Enter the ID to delete"));
        Customer objCustomer = (Customer) this.objCustomerModel.findById(idDelete);

        if(objCustomer == null){
            JOptionPane.showMessageDialog(null,"Customer not found.");
        }else{
            confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete? \n" + objCustomer.toString());
            if (confirm == 0){
                this.objCustomerModel.delete(objCustomer);
            }
        }
    }
}
