package com.infinity.devmarket.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Назва є обов'язковим полем")
    @Size(min = 2, max = 30, message = "Довжина назви має бути від 2 до 30")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Опис є обов'язковим полем")
    @Size(min = 10, max = 255, message = "Довжина опису має буди від 10 до 255")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Ціна є обов'язковим полем")
    @DecimalMin(value = "0.000001", message = "Ціна має бути більшою")
    @Column(name = "price")
    private BigDecimal price;

    public Product() { }

    public Product(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
