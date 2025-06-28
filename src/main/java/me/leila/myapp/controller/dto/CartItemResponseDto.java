package me.leila.myapp.controller.dto;

import lombok.Data;

import java.util.List;
@Data
public class CartItemResponseDto {
    private List<CartItemDto> cartItemDtos;

}
