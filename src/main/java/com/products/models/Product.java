package com.products.models;

import com.products.dto.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "product")
@Entity(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private Double price;

    public Product(ProductRequestDTO productRequestDTO){
        this.name = productRequestDTO.name();
        this.price = productRequestDTO.price();
    }
}
