package me.leila.myapp.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class OrderItemDto {
    private Long productId;
    private BigDecimal price;
    private Integer quantity;
}

