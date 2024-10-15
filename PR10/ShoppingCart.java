package src;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Product> products = new ArrayList<>();
    private double total;

    public void addProduct(Product product) {
        products.add(product);
        total += product.getPrice();
    }

    public double calculateTotalDiscount() {
        double totalDiscount = 0;
        for (Product product : products) {
            totalDiscount += product.getDiscount();
        }
        return totalDiscount;
    }

    public void displayCart() {
        System.out.println("Shopping Cart:");
        for (Product product : products) {
            System.out.println(product);
        }
        double totalDiscount = calculateTotalDiscount();
        System.out.println("Total price before discount: $" + total);
        System.out.println("Total discount: $" + totalDiscount);
        System.out.println("Final total: $" + (total - totalDiscount));
    }
}
