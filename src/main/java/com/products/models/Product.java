package com.products.models;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "product")
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private Double price;
}
