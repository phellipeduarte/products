package com.products.controllers;

import com.products.dto.ProductRequestDTO;
import com.products.dto.ProductResponseDTO;
import com.products.models.Product;
import com.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable(value = "id") UUID id){
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not Found");
        }
        ProductResponseDTO product = new ProductResponseDTO(productOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

}
