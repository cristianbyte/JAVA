package entity;

public class Purchase {
    private int id;
    private String purchase_date;
    private int quantity;
    private int id_customer;
    private String name_customer;
    private String email_customer;
    private int id_product;
    private String name_product;
    private Double price_product;
    private String name_store;
    private String location_store;


    public Purchase(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail_customer() {
        return email_customer;
    }

    public void setEmail_customer(String email_customer) {
        this.email_customer = email_customer;
    }

    public Double getPrice_product() {
        return price_product;
    }

    public void setPrice_product(Double price_product) {
        this.price_product = price_product;
    }

    public String getName_store() {
        return name_store;
    }

    public void setName_store(String name_store) {
        this.name_store = name_store;
    }

    public String getLocation_store() {
        return location_store;
    }

    public void setLocation_store(String location_store) {
        this.location_store = location_store;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public String getName_customer() {
        return name_customer;
    }

    public void setName_customer(String name_customer) {
        this.name_customer = name_customer;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", purchase_date='" + purchase_date + '\'' +
                ", quantity=" + quantity +
                ", id_customer=" + id_customer +
                ", name_customer='" + name_customer + '\'' +
                ", id_product=" + id_product +
                ", name_product='" + name_product + '\'' +
                '}';
    }

    public String toString(boolean names) {
        return "Purchase " +
                "id='"+ id + '\'' +
                ", purchase_date='" + purchase_date + '\'' +
                ", quantity=" + quantity +
                (names ? ", name_customer='" + name_customer + '\'' + ", name_product='" + name_product + '\'' : ", id_customer=" + id_customer +", id_product=" + id_product);
    }

    public String bill(){
        return " ******  EDNA OUTLET ****** \n" +
                "Date: " + purchase_date + " - Store: " + name_store + "\n" +
                "Client Name: " + name_customer + "\n" +
                name_product + " x " + quantity + "\n" +
                "Price: " + price_product*0.81*quantity + "\n" +
                "IVA: " + price_product*0.19*quantity + "\n" + "\n" + "\n"+

                "Total Amount:      " + + quantity*price_product;



    }


}
