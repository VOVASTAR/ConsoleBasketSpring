package edu.hillel.ConsoleBasket.repository.cart;


import edu.hillel.ConsoleBasket.entity.Cart;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class CartRepositoryInMemoryImpl implements CartRepository {

    private final Cart cart;

    public CartRepositoryInMemoryImpl(Cart cart) {
        this.cart = cart;
    }

    @Override
    public Map<Long, Integer> getCartProduct() {
        return this.cart.getCartProduct();
    }

    @Override
    public void addProductToCartById(Long productId, Integer quantityOfProducts) {
        this.cart.getCartProduct().merge(productId, quantityOfProducts,
                (prodId, prodCartQuantity) -> prodCartQuantity + quantityOfProducts);
    }

    @Override
    public void removeProductById(Long productId) {
        this.cart.getCartProduct().remove(productId);
    }
}

