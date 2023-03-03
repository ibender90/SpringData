package ru.geek.market.api.DTO;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {
    private Long id;
    private BigDecimal totalPriceFromCart;
    private List<OrderItemDto> items;
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalPriceFromCart() {
        return totalPriceFromCart;
    }

    public void setTotalPriceFromCart(BigDecimal totalPriceFromCart) {
        this.totalPriceFromCart = totalPriceFromCart;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
