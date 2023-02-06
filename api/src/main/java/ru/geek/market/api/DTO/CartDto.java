package ru.geek.market.api.DTO;

import java.util.List;


public class CartDto {
    private List<CartItemDto> cartItems;
    private Double totalPrice;

    public CartDto(List<CartItemDto> cartItems, Double totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public CartDto() {
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
