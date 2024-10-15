package src;

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Product apple = new FoodProduct("Apple", 1.25);
        Product banana = new FoodProduct("Banana", 0.75);
        Product orange = new FoodProduct("Orange", 1.50);
        Product laptop = new ElectronicsProduct("Laptop", 999.99);
        Product smartphone = new ElectronicsProduct("Smartphone", 799.99);

        System.out.println("Available products:");
        System.out.println(apple);
        System.out.println(banana);
        System.out.println(orange);
        System.out.println(laptop);
        System.out.println(smartphone);

        System.out.println("\nUser buys: Apple, Laptop, and Smartphone");
        cart.addProduct(apple);
        cart.addProduct(laptop);
        cart.addProduct(smartphone);

        cart.displayCart();
    }
}
