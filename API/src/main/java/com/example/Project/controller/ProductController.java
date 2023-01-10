package com.example.Project.controller;

import com.example.Project.repository.ProductRepository;
import com.example.Project.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
            return productRepository.findByNameContaining(filter).stream().limit(50).collect(Collectors.toList());
        }
    }

    @GetMapping("/test")
    @ApiOperation(value = "Otrzymanie listy produktów")
    public List<Product> putNewRandomProduct() {
        var p = new Product(null,"mars",0.1,0.1,10.0,10.0);
        var a = new Product(null,"mleko",0.1,0.1,10.0,10.0);
        var b = new Product(null,"makrel",0.1,0.1,10.0,10.0);
        var c = new Product(null,"marchew",0.1,0.1,10.0,10.0);
        var d = new Product(null,"mors",0.1,0.1,10.0,10.0);
        var e = new Product(null,"manna",0.1,0.1,10.0,10.0);
        var g = new Product(null,"mod",0.1,0.1,10.0,10.0);
        var h = new Product(null,"mor",0.1,0.1,10.0,10.0);

        productRepository.save(p);
        productRepository.save(a);
        productRepository.save(b);
        productRepository.save(c);
        productRepository.save(e);
        productRepository.save(d);
        productRepository.save(g);
        productRepository.save(h);
        return productRepository.findAll();
    }

}
