package com.products.dto;

import com.products.models.Product;

import java.util.UUID;

public record ProductResponseDTO(UUID id, String name, Double price) {

    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice());
    }
}
