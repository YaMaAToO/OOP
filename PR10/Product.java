package src;

public abstract class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract double getDiscount();

    public String toString() {
        return name + ": $" + price + " (Discount: $" + getDiscount() + ")";
    }
}

