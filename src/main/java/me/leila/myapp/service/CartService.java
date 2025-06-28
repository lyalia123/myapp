package me.leila.myapp.service;

import lombok.RequiredArgsConstructor;
import me.leila.myapp.controller.dto.CartItemDto;
import me.leila.myapp.controller.dto.CartItemResponseDto;
import me.leila.myapp.model.Usr;
import me.leila.myapp.repository.UserRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final UserService userService;
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String PREFIX = "cart:";

    private String getKey(Long userId) {
        return PREFIX + userId;
    }

    public CartItemResponseDto getCart(Long userId) {
        CartItemResponseDto cartItemResponseDto = new CartItemResponseDto();
        List<Object> items = redisTemplate.opsForList().range(getKey(userId), 0, -1);
        List<CartItemDto> result = new ArrayList<>();
        if (items != null) {
            for (Object o : items) {
                if (o instanceof CartItemDto item) {
                    result.add(item);
                }
            }
        }
        cartItemResponseDto.setCartItemDtos(result);
        return cartItemResponseDto;
    }

    public void addItem( CartItemDto item) {
        Usr usr=userService.getUserById(item.getUserId()).orElseThrow(()->new RuntimeException("<UNK>"));
        redisTemplate.opsForList().rightPush(getKey(usr.getId()), item);
    }

    public void removeItem( CartItemDto item) {
        redisTemplate.opsForList().remove(getKey(item.getUserId()), 1, item);
    }

    public void clearCart(Long userId) {
        redisTemplate.delete(getKey(userId));
    }
}
