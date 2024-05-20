package com.products.controllers;

import com.products.dto.ProductResponseDTO;
import com.products.models.Product;
import com.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<ProductResponseDTO> getAll(){
        List<ProductResponseDTO> productList = productRepository.findAll().stream().map(ProductResponseDTO::new).toList();
        return productList;
    }
}
