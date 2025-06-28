package me.leila.myapp.controller;

import me.leila.myapp.controller.dto.CreateOrderRequest;
import me.leila.myapp.model.Order;
import me.leila.myapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody CreateOrderRequest request) {
        Order order = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getUserOrders(
            @RequestParam(name = "userId") Long userId) {
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }

}
