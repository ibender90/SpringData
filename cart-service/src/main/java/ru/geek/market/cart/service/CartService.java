package ru.geek.market.cart.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geek.market.api.DTO.ProductDto;
import ru.geek.market.cart.integrations.ProductServiceIntegration;
import ru.geek.market.cart.model.Cart;


@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart cart;

    @PostConstruct
    public void initCart() {
        cart = new Cart();
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addProductToCart(Long productID) {
        ProductDto product = productServiceIntegration.findById(productID);
        if (product != null) {
            cart.addProductToCart(product);
        } //todo throw product not found
    }

    public void increaseProductQuantity(Long id) {
        cart.increaseItemQuantity(id);
    }

    public void decreaseProductQuantity(Long id) {
        cart.decreaseItemQuantity(id);
    }

    public void removeProduct(Long id) {
        cart.removeProduct(id);
    }

    public void clearCart() {
        this.cart = new Cart();
    }

}
