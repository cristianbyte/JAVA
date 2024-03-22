import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static int message(String dialogue){
        int option;
        try{
            option = Integer.parseInt(JOptionPane.showInputDialog(null,dialogue));
        }catch (Exception e){
            return 0;
        }
        return option;
    }
    public static void main(String[] args) {
        ArrayList<Worker> employeeList = new ArrayList<>();

        PermanentEmployee carl = new PermanentEmployee(2037849,23,"Carlos Zuñiga",2000,1,"01-02-2020");
        TemporaryEmployee jaimito =  new TemporaryEmployee(234325,34,"Jaime Julian Torres",2400,2,"RIOT","01-05-2024");

        employeeList.add(carl);
        employeeList.add(jaimito);

        StringBuilder list = new StringBuilder();
        int i = 0;
        for(Worker employee : employeeList){
            list.append(i).append(": ").append(employee.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, list.toString());
    }
}
