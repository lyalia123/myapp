package me.leila.myapp.controller.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CreateOrderRequest {
    private Long userId;
    private List<OrderItemDto> items;

}
