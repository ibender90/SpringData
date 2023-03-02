package ru.geek.market.cart.model;

import lombok.extern.slf4j.Slf4j;
import ru.geek.market.api.DTO.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
public class Cart {
    private List<CartItem> cartItems;

    public Cart(List<CartItem> cartItems, BigDecimal totalPrice) { //for testing
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(cartItems);
    }

    private BigDecimal totalPrice;

    public Cart() {
        this.cartItems = new ArrayList<>();
        this.totalPrice = BigDecimal.ZERO;
    }

    public void addProductToCart(ProductDto product) {
        if (!cartItems.isEmpty()) {
            for (CartItem item : cartItems) {
                if (Objects.equals(item.getProductId(), product.getId())) {
                    item.setQuantity(item.getQuantity() + 1);
                    item.recalculatePriceForItem();
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
        totalPrice = BigDecimal.ZERO;
        if (!cartItems.isEmpty()) {
            for (CartItem item : cartItems)
                totalPrice = totalPrice.add(item.getPriceCalculated());
        }
    }

    public void increaseItemQuantity(Long id) {
        for (CartItem item : cartItems) {
            if (item.getProductId().equals(id)) {
                item.setQuantity(item.getQuantity() + 1);
                item.recalculatePriceForItem();
                calculateTotalPrice();
            }
        }
    }

    public void decreaseItemQuantity(Long id) {
        for (CartItem item : cartItems) {
            if (item.getProductId().equals(id)) {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                    item.recalculatePriceForItem();
                    calculateTotalPrice();
                }
                else {
                    removeProduct(item.getProductId());
                }
            }
        }
    }

    public void removeProduct(Long id) {
        cartItems.removeIf(item -> item.getProductId().equals(id));
        calculateTotalPrice();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
