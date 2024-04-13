package com.infinity.devmarket.repositories;

import com.infinity.devmarket.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByPersonId(Long customerId);
}
