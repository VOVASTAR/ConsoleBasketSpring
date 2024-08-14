package edu.hillel.ConsoleBasket.repository.cart;

import java.util.Map;

public interface CartRepository {
    Map<Long, Integer> getCartProduct();

    void addProductToCartById(Long itemId, Integer numberOfItems);

    void removeProductById(Long id);

}
