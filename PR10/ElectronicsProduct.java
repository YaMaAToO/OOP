package src;

public class ElectronicsProduct extends Product {
    public ElectronicsProduct(String name, double price) {
        super(name, price);
    }

    public double getDiscount() {
        return getPrice() * 0.15;
    }
}
