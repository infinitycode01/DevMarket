package com.infinity.devmarket.services;

import com.infinity.devmarket.models.Order;
import com.infinity.devmarket.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findPersonOrders(Long personId) {
        return orderRepository.findByPersonId(personId);
    }

    @Transactional
    public void save(Order order) {
        orderRepository.save(order);
    }
}
