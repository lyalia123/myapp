package me.leila.myapp.service;

import me.leila.myapp.controller.dto.CreateOrderRequest;
import me.leila.myapp.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(CreateOrderRequest request);
    List<Order> getUserOrders(Long userId);
}

