package edu.hillel.ConsoleBasket.repository.product;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import edu.hillel.ConsoleBasket.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProductRepositoryInMemoryImpl implements ProductRepository {
    private List<Product> allItems;

    private ProductRepositoryInMemoryImpl() {
        initialize();
    }

    private void initialize() {
        Faker faker = new Faker();
        allItems = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Book book = faker.book();
            Product build = Product.builder()
                    .productId((long) i)
                    .productName(book.author() + " " + book.title())
                    .productPrice(Math.random() * 100 + 15)
                    .build();
            allItems.add(build);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return this.allItems;
    }

    @Override
    public Product getProductById(Long id) {
        return this.allItems.stream()
                .filter(product -> product.getProductId().equals(id))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("No product with id " + id));
    }

}
