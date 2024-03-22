package entity;

public class Book {
    private int id;
    private String title;
    private int publication_year;
    private double price;
    private int idAuthor;

    private Author objAuthor;

    public Book(){}

    public Book(int id, String title, int publication_year, double price, int idAuthor, Author objAuthor) {
        this.id = id;
        this.title = title;
        this.publication_year = publication_year;
        this.price = price;
        this.idAuthor = idAuthor;
        this.objAuthor = objAuthor;
    }

    public Author getObjAuthor() {
        return objAuthor;
    }

    public void setObjAuthor(Author objAuthor) {
        this.objAuthor = objAuthor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    @Override
    public String toString() {
        return "Title: " + title + '\'' +
                ", Publication_year: " + publication_year +
                ", Price: " + price +
                ", Author: " + this.objAuthor.getName() ;
    }
}
