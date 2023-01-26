package com.example.Project.controller;

import com.example.Project.repository.ProductRepository;
import com.example.Project.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;


import java.util.List;

@RestController
@RequestMapping("/api/data")
@Api(value = "Produkty")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/product") //product?filter=
    @ApiOperation(value = "Otrzymanie listy produktów")
    public List<Product> getAllProduct(@RequestParam(name = "filter", required = false) String filter) {
        if (filter == null) {
            return productRepository.findAll();
        } else {
            return productRepository.findByNameContainingIgnoreCase(filter.toLowerCase()).stream().limit(50).collect(Collectors.toList());
        }
    }

    @PostMapping("/dodanie")
    @ApiOperation(value = "Sprawdzenie czy istnieje dany produkt")
    public Product checkAndAddProduct(@RequestBody Product product) {
        Optional<Product> existingProduct = productRepository.findByNameAndBialkaAndTluszczeAndWeglowodaneAndGrams(product.getName(), product.getBialka(), product.getTluszcze(), product.getWeglowodane(), product.getGrams());
        if (existingProduct.isPresent()) {
            return existingProduct.get();
        } else {
            return productRepository.save(product);
        }
    }
   // curl -X POST http://localhost:8080/api/data/test -d '{"id":1,"name":"Product 1","bialka":10.0,"tluszcze":30.0,"weglowodane":50.0,"grams":100.0}' -H "Content-Type: application/json"
   /*  {
        "name" : "Product 1",
        "bialka" : 10.0,
        "tluszcze" : 30.0,
        "weglowodane" : 50.0,
        "grams" : 100.0
        }


        mvn spring-boot:run -Dspring-boot.run.arguments=--spring.datasource.backup.operation=backup

        ./mvnw spring-boot:run -Dspring-boot.run.arguments=--spring.datasource.backup.operation=restore

        mvn liquibase:update

*/
}