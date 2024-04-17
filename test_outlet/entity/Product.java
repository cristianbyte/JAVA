package entity;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int id_store;

    private String name_store;

    public String getName_store() {
        return name_store;
    }

    public void setName_store(String name_store) {
        this.name_store = name_store;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId_store() {
        return id_store;
    }

    public void setId_store(int id_store) {
        this.id_store = id_store;
    }

    public Product(int id, String name, double price, int stock, int id_store) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.id_store = id_store;
    }

    public Product(){}

    public String toString(boolean names) {
        return "Product " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                (names ? ", name_store=" + name_store : ", id_store=" + id_store) ;
    }

    @Override
    public String toString() {
        return "Product " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", id_store=" + id_store ;
    }
}
