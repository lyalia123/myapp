package me.leila.myapp.service;

import me.leila.myapp.controller.dto.CreateOrderRequest;
import me.leila.myapp.controller.dto.OrderItemDto;
import me.leila.myapp.model.Order;
import me.leila.myapp.model.OrderItem;
import me.leila.myapp.model.OrderStatus;
import me.leila.myapp.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(CreateOrderRequest request) {
        List<OrderItemDto> itemsDto = request.getItems();
        BigDecimal total = itemsDto.stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setTotalPrice(total);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());


        List<OrderItem> items = itemsDto.stream().map(dto -> {
            OrderItem item = new OrderItem();
            item.setProductId(dto.getProductId());
            item.setPrice(dto.getPrice());
            item.setQuantity(dto.getQuantity());
            item.setOrder(order);
            return item;
        }).toList();

        order.setItems(items);

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
