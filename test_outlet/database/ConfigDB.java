package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConfigDB {
    //Variables that will contain connection's states.
    static Connection objConnection = null;

    //Method to establish the connection between java and database.
    public static Connection openConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test_db_java";
            String user = "root";
            String password = "Rlwl2023.";

            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("Connection successful");

        }catch (ClassNotFoundException e){
            System.out.println("Error >> Driver not installed: "+ e.getMessage());
        }catch (SQLException e){
            System.out.println("Error >> Connection unsuccessful");
        }
        return objConnection;
    }

    public static void closeConnection(){
        try{
            if(objConnection != null){
                objConnection.close();
                System.out.println("Connection closed.");
            }

        }catch (SQLException e){
            System.out.println("Error>> " + e.getMessage());
        }
    }
}
