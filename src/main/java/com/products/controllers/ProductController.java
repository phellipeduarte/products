package com.products.controllers;

import com.products.dto.ProductRequestDTO;
import com.products.dto.ProductResponseDTO;
import com.products.models.Product;
import com.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public void saveProduct(@RequestBody ProductRequestDTO productRequestDTO){
        Product product = new Product(productRequestDTO);
        productRepository.save(product);
        return;
    }

    @GetMapping
    public List<ProductResponseDTO> getAll(){
        List<ProductResponseDTO> productList = productRepository.findAll().stream().map(ProductResponseDTO::new).toList();
        return productList;
    }
}
