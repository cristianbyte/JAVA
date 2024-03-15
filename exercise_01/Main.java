import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        /*Ejercicio 1: Sistema de Control de Inventarios
            Objetivo: Crear un sistema para manejar el inventario de un almacén, aplicando
            encapsulamiento y herencia, y utilizando ArrayList para almacenar los productos.
            Principios de POO aplicados: Encapsulamiento, Herencia.
            Requisitos:
            Clase Producto: Base para todos los productos, con propiedades como id, nombre, y
            precio. Implementa getters y setters para aplicar el encapsulamiento.
            Clase ProductoEspecifico: Hereda de Producto y añade propiedades específicas, como
            categoria o marca.
            Clase Inventario: Utiliza un ArrayList de objetos Producto para gestionar el inventario.
            Implementa métodos para añadir, eliminar, y listar productos, además de buscar productos
            por nombre o categoría.*/

        Product car = new Product(1,"renault",1200000);
        Product car2 = new Product(2,"audi",1600000);

        ArrayList<Product> inventory = new ArrayList<>();
        inventory.add(car);
        inventory.add(car2);
        int option = 0;
        do{
            option = Integer.parseInt( JOptionPane.showInputDialog(null,"Selecciona una option: \n"+
                    "1: Add an item. \n" +
                    "2: Delete an item \n" +
                    "3: Show items \n" +
                    "4: Search item \n"));
            switch (option){
                case 1:
                    String name = JOptionPane.showInputDialog(null,"Nombre");
                    float price = (float) Integer.parseInt(JOptionPane.showInputDialog(null,"Precio: "));
                    Product item = new Product(inventory.size()+1,name,price);
                    inventory.add(item);
                    JOptionPane.showMessageDialog(null,"Añadido correctamente.");
                    break;
                case 2:
                    String list = Main.show(inventory);
                    int delete = Integer.parseInt( JOptionPane.showInputDialog(null,"Seleccione item a eliminar: \n"+list ));
                    try{
                        inventory.remove(delete-1);
                    }catch (Exception E){
                        JOptionPane.showMessageDialog(null,"Invalid");
                    }
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, Main.show(inventory));
                    break;
                case 4:
                    String key = JOptionPane.showInputDialog(null, "Buscar :");
                    Product coidicence = new Product();
                    for(Product elem : inventory ){
                        if(Objects.equals(elem.getName(), key)){
                            coidicence = elem;
                        }
                    }
                    if(coidicence.getId()!=0){
                        JOptionPane.showMessageDialog(null, coidicence.toString());
                    }else{
                        JOptionPane.showMessageDialog(null, "NO se encontraron coincidencias.");
                    }
            }
        }while(option != 0);
    }
    public static String show(ArrayList<Product> inv){
        String list  = new String();
        int index = 0;
        for(int i = 0 ; i < inv.size();i++) {
            list += ++index + ": " + inv.get(i).getName() + "\n";
            System.out.println(list);
        }
        return list;
    }
}