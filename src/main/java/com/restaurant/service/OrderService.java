package com.restaurant.service;

import com.restaurant.model.Order;
import com.restaurant.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public ArrayList<Order> getOrdersByEmail(String email) {
        return (ArrayList<Order>) orderRepository.findAllByEmail(email);
    }

    public int deleteById(int id) {
        orderRepository.deleteById(id);
        return id;
    }

    public Optional<Order> getOrder(int id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

}
