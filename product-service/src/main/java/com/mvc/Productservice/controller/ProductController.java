package com.mvc.Productservice.controller;

import com.mvc.Productservice.dto.ProductRequest;
import com.mvc.Productservice.model.ProductModel;
import com.mvc.Productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping
    public List<ProductModel> findAllProducts() {
        return productService.findAllProducts();
    }
}
