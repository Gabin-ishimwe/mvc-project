package com.mvc.Productservice.service;

import com.mvc.Productservice.dto.ProductRequest;
import com.mvc.Productservice.model.ProductModel;
import com.mvc.Productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public ResponseEntity<?> createProduct(ProductRequest productRequest) {
        ProductModel productModel = ProductModel.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(productModel);
        return new ResponseEntity<>("Product Created", HttpStatus.CREATED);
    }

    public List<ProductModel> findAllProducts() {
        log.info("Products retrieved...");
        return productRepository.findAll();
    }
}
