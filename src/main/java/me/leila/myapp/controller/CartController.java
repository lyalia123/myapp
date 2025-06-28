package me.leila.myapp.controller;


import lombok.RequiredArgsConstructor;
import me.leila.myapp.controller.dto.CartItemDto;
import me.leila.myapp.controller.dto.CartItemResponseDto;
import me.leila.myapp.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public CartItemResponseDto getCart(@RequestParam(name = "user_id") Long userId) {
        return cartService.getCart(userId);
    }

    @PostMapping("/add")
    public void addItem(@RequestBody CartItemDto item)  {
        cartService.addItem( item);
    }

    @DeleteMapping("/remove")
    public void removeItem(@RequestBody CartItemDto item) {
        cartService.removeItem( item);
    }

    @DeleteMapping("/clear")
    public void clear(@RequestParam(name = "user_id") Long userId) {
        cartService.clearCart(userId);
    }
}
