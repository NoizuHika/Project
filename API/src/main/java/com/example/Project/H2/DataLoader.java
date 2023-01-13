package com.example.Project.H2;

import com.example.Project.model.Product;
import com.example.Project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product(1L, "Product 1", 10.0, 30.0, 50.0, 100.0));
        productRepository.save(new Product(2L, "Product 2", 20.0, 40.0, 60.0, 100.0));
        productRepository.save(new Product(3L, "Product 3", 30.0, 50.0, 70.0, 100.0));
    }
}

