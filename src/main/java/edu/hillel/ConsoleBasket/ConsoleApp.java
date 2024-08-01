package edu.hillel.ConsoleBasket;

import edu.hillel.ConsoleBasket.repository.product.ProductRepository;
import edu.hillel.ConsoleBasket.service.CartService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleApp {

    private final CartService cartService;
    private final ProductRepository productRepository;

    public ConsoleApp(CartService cartService, ProductRepository productRepository) {
        this.cartService = cartService;
        this.productRepository = productRepository;
    }

    Scanner scanner = new Scanner(System.in);

    public void run() {


        while (true) {
            System.out.println(
                    "[Choose option]\n" +
                            "Your cart:\n" + cartService.getCartContent() +
                            "Total amount: " + cartService.getTotalCartAmount());
            System.out.println(
                    """
                            [Choose option]
                            1. add item to the cart
                            2. remove all items by ID from the cart
                            """);
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> addItemToCart();
                case 2 -> removeAllItemsByIdFromCart();
            }
        }
    }

    private void addItemToCart() {
        StringBuilder items = new StringBuilder("Enter id of item you want to buy:");
        productRepository.getAllProducts().forEach(item -> items.append("\n").append(item.toString()));
        System.out.println(items);
        Long id = scanner.nextLong();
        if (cartService.isProductNOTAvailableInStore(id)) return;
        System.out.println("Enter how much do you want to buy");
        Integer numberOfItems = scanner.nextInt();
        cartService.addProductToCartById(id, numberOfItems);
    }

    private void removeAllItemsByIdFromCart() {
        System.out.println("Enter id of items you want to remove:");
        Long id = scanner.nextLong();
        cartService.removeProductById(id);
    }


}
