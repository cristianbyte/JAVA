public class SpecificProduct extends Product {
    public enum category{
        KITCHEN,
        TOOLS,
        PERSONAL_ITEMS
    }
    private category ccategory;
    private String brand;

    public SpecificProduct(int id, String name, float price, SpecificProduct.category pcategory, String brand) {
        super(id, name, price);
        this.ccategory = pcategory;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "SpecificProduct{" +
                "category=" + ccategory +
                ", brand='" + brand + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
