package ru.geek.market.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer quantity;
    private BigDecimal priceCalculated;

    public void recalculatePriceForItem(){
        priceCalculated = productPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
