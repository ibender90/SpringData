package ru.geek.market.cart.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.geek.market.api.DTO.ProductDto;
import ru.geek.market.cart.integrations.ProductServiceIntegration;
import ru.geek.market.cart.model.Cart;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;
    @Value("${cart-service.cart-prefix}") //for redis
    private String cartPrefix;

    public Cart getCurrentCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if(!redisTemplate.hasKey(targetUuid)){
            redisTemplate.opsForValue().set(targetUuid, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(targetUuid);
    }

    public void addProductToCart(String uuid, Long productID) {
        ProductDto product = productServiceIntegration.findById(productID);
        execute(uuid, cart -> cart.addProductToCart(product));
    }

    public void increaseProductQuantity(String uuid, Long id) {
        execute(uuid, cart -> cart.increaseItemQuantity(id));
    }

    public void decreaseProductQuantity(String uuid, Long id) {
        execute(uuid, cart -> cart.decreaseItemQuantity(id));
    }

    public void removeProduct(String uuid, Long id) {
        execute(uuid, cart -> cart.removeProduct(id));
    }

    public void clearCart(String uuid) {
        execute(uuid, Cart::clear);
    }

    private void execute(String uuid, Consumer<Cart> operation){
        Cart cart = getCurrentCart(uuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(cartPrefix + uuid, cart);
    }

}
