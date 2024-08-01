package edu.hillel.ConsoleBasket.repository.product;


import edu.hillel.ConsoleBasket.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> getAllProducts();

    Product getProductById(Long id);

}
