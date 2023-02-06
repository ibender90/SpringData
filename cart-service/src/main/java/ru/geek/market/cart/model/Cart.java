package ru.geek.market.cart.model;

import lombok.Data;
import ru.geek.market.api.DTO.ProductDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Data
public class Cart {
    private List<CartItem> cartItems;

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(cartItems);
    }

    private Double totalPrice;

    public Cart() {
        this.cartItems = new ArrayList<>();
        this.totalPrice = 0.0D;
    }

    public void addProductToCart(ProductDto product) {
        if (!cartItems.isEmpty()) {
            for (CartItem item : cartItems) {
                if (Objects.equals(item.getProductId(), product.getId())) {
                    item.setQuantity(item.getQuantity() + 1);
                    item.setPriceCalculated(item.getProductPrice() * item.getQuantity());
                    calculateTotalPrice();
                    return;
                }
            }
        }
        cartItems.add(new CartItem
                (product.getId(), product.getName(), product.getPrice(), 1, product.getPrice()));
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        totalPrice = 0.0D;
        if (!cartItems.isEmpty()) {
            for (CartItem item : cartItems)
                totalPrice += (item.getProductPrice() * item.getQuantity());
        }
    }

    public void increaseProductQuantity(Long id) {
        for (CartItem item : cartItems) {
            if (item.getProductId().equals(id)) {
                item.setQuantity(item.getQuantity() + 1);
                calculateTotalPrice();
            }
        }
    }

    public void decreaseProductQuantity(Long id) {
        for (CartItem item : cartItems) {
            if (item.getProductId().equals(id)) {

                if (item.getQuantity() != 1) {
                    item.setQuantity(item.getQuantity() - 1);
                    calculateTotalPrice();
                }
            }
        }
    }

    public void removeProduct(Long id) {
        cartItems.removeIf(item -> item.getProductId().equals(id));
        calculateTotalPrice();
    }
}