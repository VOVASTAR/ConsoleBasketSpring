package edu.hillel.ConsoleBasket.service;

import edu.hillel.ConsoleBasket.entity.Product;
import edu.hillel.ConsoleBasket.repository.cart.CartRepository;
import edu.hillel.ConsoleBasket.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Map<Long, Integer> getCart() {
        return cartRepository.getCartProduct();
    }

    public boolean isProductNOTAvailableInStore(Long id) {
        boolean isPresent = productRepository.getAllProducts().stream()
                .anyMatch(product -> product.getProductId().equals(id));
        if (!isPresent) {
            System.out.println("No such id in market");
        }
        return !isPresent;
    }

    public void addProductToCartById(Long itemId, Integer numberOfItems) {
        if (isProductNOTAvailableInStore(itemId)) return;
        cartRepository.addProductToCartById(itemId, numberOfItems);
    }

    public String getCartContent() {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Long, Integer> addedProducts = cartRepository.getCartProduct();
        for (Map.Entry<Long, Integer> entry : addedProducts.entrySet()) {
            Product productById = productRepository.getProductById(entry.getKey());
            stringBuilder
                    .append("  [ID:")
                    .append(entry.getKey())
                    .append("] ")
                    .append(productById.getProductName())
                    .append(" (")
                    .append(entry.getValue())
                    .append(")\n");
        }
        return stringBuilder.toString();
    }

    public Double getTotalCartAmount() {
        double totalAmount = 0.0;
        Map<Long, Integer> addedItems = cartRepository.getCartProduct();
        for (Map.Entry<Long, Integer> entry : addedItems.entrySet()) {
            Product productById = productRepository.getProductById(entry.getKey());
            totalAmount += productById.getProductPrice() * entry.getValue();
        }
        return totalAmount;
    }


    public void removeProductById(Long id) {
        if (isProductNOTAvailableInStore(id)) {
            return;
        }
        if (!this.getCart().containsKey(id)) {
            System.out.println("No such id in current cart");
            return;
        }
        try {
            cartRepository.removeProductById(id);
        } catch (IllegalArgumentException e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }
    }
}
