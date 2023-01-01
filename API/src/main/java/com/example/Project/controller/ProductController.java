package com.example.Project.controller;

import com.example.Project.repository.ProductRepository;
import com.example.Project.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/data")
@Api(value = "Produkty")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/product")
    @ApiOperation(value = "Otrzymanie listy produktów")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
        // otrzymanie listy produktów
    }


    @GetMapping("/test")
    @ApiOperation(value = "Otrzymanie listy produktów")
    public List<Product> putNewRandomProduct() {
        var p = new Product(null,"something",0.1,0.1,Math.random() * 10.0,10.0);
        productRepository.save(p);
        return productRepository.findAll();
    }

}