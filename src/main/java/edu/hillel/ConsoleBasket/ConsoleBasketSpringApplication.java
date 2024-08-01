package edu.hillel.ConsoleBasket;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsoleBasketSpringApplication {


    public static void main(String[] args) {
        SpringApplication.run(ConsoleBasketSpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(ConsoleApp consoleApp) {
        return args -> consoleApp.run();
    }
}

