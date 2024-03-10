import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// class representing the products available in the store
class Product {

    static String[] name = {"ASUS ROG Zephyrus", "Alienware m15 R4", "MSI GS66 Stealth", "Razer Blade 15", "Acer Predator 300 ", "Lenovo Legion 7i", "Razer Blade 15", "Gigabyte Aorus 15G", "ROG Strix Scar", "Dell G3 15"};
    static int[] price = {2500, 2000, 2180, 1800, 2100, 2300, 1750, 1950, 1700};
}

// class representing an item in the user's shopping cartt
class CartItem {

    String itemName;
    int itemPrice;
    int quantity;

    // to initialize CartItem
    //the keywodr "this" refers to the current object instance
    public CartItem(String itemName, int itemPrice, int quantity) {
        this.itemName = itemName; // 
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public String toString() {
        return "Item: " + itemName + "\tPrice: " + itemPrice + "\tQuantity: " + quantity;
    }
}

// Class representing the user's shopping cart
class UserCart {

    List<CartItem> cartItems;

    // to initialize UserCart
    public UserCart() {
        this.cartItems = new ArrayList<>();
    }

    // Check if the cart already contains an item with the same name if it is just get the quanity.
    public boolean containsItem(CartItem newItem) {
        for (CartItem item : cartItems) {
            if (item.itemName.equals(newItem.itemName)) {
                return true;
            }
        }
        return false;
    }

    // Update the quantity of an existing item in the cart
    public void updateItemQuantity(CartItem newItem, int quantity) {
        for (CartItem item : cartItems) {
            if (item.itemName.equals(newItem.itemName)) {
                item.quantity += quantity;
                break;
            }
        }
    }

    // Add a new item to the cart
    public void addItem(CartItem newItem) {
        cartItems.add(newItem);
    }

    // Retrieve the list of items in the cart
    public List<CartItem> getCartItems() {
        return cartItems;
    }
}

// Main class representing the shopping application
public class Shopping {

    static Scanner scan = new Scanner(System.in);
    static UserCart userCart = new UserCart();

    // Main method to start the application
    public static void main(String[] args) {
        intro();
    }

    // Method to generate and display the available products
    static void generateProduct() {
        // Display the list of available products and their prices
        for (int x = 0; x < Product.name.length && x < Product.price.length; x++) {
            String productName = Product.name[x];
            int productPrice = Product.price[x];

            System.out.println("[" + (x + 1) + "]" + productName + "\t\tprice:" + productPrice);
        }
    }

    // Method to display the introduction and main menu
    static void intro() {
        System.out.println("\nWelcome to IT's Store\n");
        System.out.println("Available Items");
        System.out.println("----------------------------------------------");
        generateProduct();
        System.out.println("----------------------------------------------");

        // Display main menu options
        System.out.print("\n[1]ADD TO CART\n[2]VIEW CART\n[3]BUY\n[4]CANCEL\n\nCHOICE: ");
        int userNumPick = scan.nextInt();
        addToCart(userNumPick);
    }

    // m to handle the user's choice from the main menu
    static void addToCart(int userNumPick) {
        switch (userNumPick) {
            case 1:
                System.out.println("\n[ADD TO CART]:\n");
                addItemToCart();
                break;
            case 2:

                viewCart();

                break;
            case 3:
                // Complete the purchase
                completePurchase();
                break;
            case 4:
                System.out.println("Thank you for coming!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    // to handle the completion of the purchase
    static void completePurchase() {
        List<CartItem> cartItems = userCart.getCartItems();

        if (cartItems.isEmpty()) {
            // Inform the user if the cart is empty
            System.out.println("Your cart is empty. Add items before buying.");
        } else {
            // Display the items in the cart
            System.out.println("\nYour Cart:");
            for (CartItem item : cartItems) {
                System.out.println(item);
            }

            // Calculate and display the total amount
            int totalAmount = calculateTotalAmount(cartItems);
            System.out.println("\nTotal Amount to pay: " + totalAmount);

            System.out.print("Enter you money for paying: ");
            int userMoney = scan.nextInt();
            int totaExchange = (userMoney - totalAmount  );
           
            if ((totaExchange - totalAmount) <= 0) {
                System.out.println("Your current balance is insufficient to complete the purchase of this item.");

            } else {
                System.out.println("Your total change: " + totaExchange);
                 System.out.print("Confirm Purchase? [1]Yes [2]No\nChoice: ");
            int confirmChoice = scan.nextInt();

            if (confirmChoice == 1) {
                // Implement logic for completing the purchase
                System.out.println("Thank you for your purchase! Your order has been placed.");
                // Clear the cart after purchase
                userCart.getCartItems().clear();
            } else {
                // Handle the case where the user chooses not to confirm the purchase
                System.out.println("Purchase canceled. Items remain in the cart.");
            }
            }

            // Ask for confirmation to complete the purchase
           
        }

        // Return to the main menu
        intro();
    }

    // Method to calculate the total amount of items in the cart
    static int calculateTotalAmount(List<CartItem> cartItems) {
        int totalAmount = 0;
        for (CartItem item : cartItems) {
            totalAmount += item.itemPrice * item.quantity;
        }
        return totalAmount;
    }

    // Method to handle the addition of items to the cart
    static void addItemToCart() {
        generateProduct();
        System.out.print("\n[0]go back\nchoice:");
        int userChoice = scan.nextInt();

        switch (userChoice) {
            case 0:
                // Return to the main menu
                intro();
                break;
            default:
                userChoice -= 1;
                String selectedProduct = Product.name[userChoice];
                int selectedProductPrice = Product.price[userChoice];

                System.out.println("You've chosen: " + selectedProduct + "\t\tprice: " + selectedProductPrice);
                System.out.print("Quantity: ");
                int getProductQuantity = scan.nextInt();

                CartItem newItem = new CartItem(selectedProduct, selectedProductPrice, getProductQuantity);

                if (userCart.containsItem(newItem)) {
                    // If the item is already in the cart, just only update its quantity
                    userCart.updateItemQuantity(newItem, getProductQuantity);
                } else {
                    // If the item is not in the cart, add it
                    userCart.addItem(newItem);
                }

                System.out.print("[1]proceed " + "[2]cancel\n\nchoice: ");
                int userChosen = scan.nextInt();

                switch (userChosen) {
                    case 1:
                        System.out.println("----------------------------------------------");
                        System.out.println("You've successfully added to the cart!");
                        System.out.println("----------------------------------------------\n");
                        // Return it to main menu
                        intro();
                        break;
                    case 2:
                        // just Return it again to the main menu
                        intro();
                        break;
                    default:
                        System.out.println("invalid!");
                }
        }
    }

    // Method to view and display the items in the user's cart
    static void viewCart() {
        List<CartItem> cartItems = userCart.getCartItems();

        if (cartItems.isEmpty()) {
            // Inform the user if the cart is empty
            System.out.println("Your cart is empty. Add items to view.");
        } else {
            // Display the items in the cart
            System.out.println("\nYour Cart:");
            for (CartItem item : cartItems) {
                System.out.println(item);

            }
        }

        // loop it again.
        intro();
    }
}
