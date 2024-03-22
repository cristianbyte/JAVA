import controller.AuthorController;
import controller.BookController;

import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        AuthorController objAuthorController = new AuthorController();
        BookController objBookController = new BookController();
        String option = "";
        do{
            option = JOptionPane.showInputDialog("""
                    MENU
                    1. Manage Author
                    2. Manage Books
                    3. List books by author
                    4. Exit
                    """);  
            switch (option){
                case "1":
                    do{
                        option = JOptionPane.showInputDialog("""
                            MENU
                            1. List authors
                            2. Add author
                            3. Update author
                            4. Delete author
                            5. Back
                        """);
                        switch (option){
                            case "1":
                                objAuthorController.getAll();
                                break;
                            case "2":
                                objAuthorController.create();
                                break;
                            case "3":
                                objAuthorController.update();
                                break;
                            case "4":
                                objAuthorController.delete();
                                break;
                        }
                    }while(!option.equals("5"));
                    break;
                case "2":
                    do{
                        option = JOptionPane.showInputDialog("""
                            MENU
                            1. List books
                            2. Add book
                            3. Update book
                            4. Delete book
                            5. Back
                        """);
                        switch (option){
                            case "1":
                                objBookController.getAll();
                                break;
                            case "2":
                                objBookController.create();
                                break;
                            case "3":
                                objBookController.update();
                            case "4":
                                objBookController.delete();
                                break;
                        }
                    }while(!option.equals("5"));
                    break;
                case "3":
                    objBookController.searchByAuthor();
                    break;
            }
        }while(!option.equals("4"));

    }
}