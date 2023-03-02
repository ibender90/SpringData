package ru.geek.market.cart.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.geek.market.api.DTO.ProductDto;
import ru.geek.market.cart.integrations.ProductServiceIntegration;
import ru.geek.market.cart.model.Cart;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;
    private Map<String, Cart> carts;

    @PostConstruct
    public void initCart() {
        carts = new HashMap<>();
    }

    public Cart getCurrentCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if(!carts.containsKey(targetUuid)){
            carts.put(targetUuid, new Cart());
        }
        return carts.get(targetUuid);
    }

    public void addProductToCart(String uuid, Long productID) {
        ProductDto product = productServiceIntegration.findById(productID);
        if (product != null) {
            getCurrentCart(uuid).addProductToCart(product);
        }
    }

    public void increaseProductQuantity(String uuid, Long id) {
        getCurrentCart(uuid).increaseItemQuantity(id);
    }

    public void decreaseProductQuantity(String uuid, Long id) {
        getCurrentCart(uuid).decreaseItemQuantity(id);
    }

    public void removeProduct(String uuid, Long id) {
        getCurrentCart(uuid).removeProduct(id);
    }

    public void clearCart(String uuid) {
        getCurrentCart(uuid).getItems().clear();
    }

}
