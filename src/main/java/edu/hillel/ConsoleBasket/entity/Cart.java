package edu.hillel.ConsoleBasket.entity;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@Scope("prototype")
public class Cart {
    private Map<Long, Integer> cartProduct;

    @PostConstruct
    private void init() {
        this.cartProduct = new HashMap<>();
    }
}
