package src;

public class FoodProduct extends Product {
    public FoodProduct(String name, double price) {
        super(name, price);
    }

    public double getDiscount() {
        return 0;
    }
}
