package com.infinity.devmarket.repositories;

import com.infinity.devmarket.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
