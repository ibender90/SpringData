package ru.geek.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_price_from_cart")
    private Double totalPriceFromCart;

    @Transient
    private HashMap<Long, Integer> productIdAndQuantity;

    @Column(name = "userId")
    private Long userId;

    public Order(Cart cart, Long userId) {
        getProductIdAndQuantityFromCartItems(cart.getItems());
        this.userId = userId;
        this.totalPriceFromCart = cart.getTotalPrice();
    }

    private void getProductIdAndQuantityFromCartItems(List<CartItem> cartItems) {
        productIdAndQuantity = new HashMap<>();
        for (CartItem item :
                cartItems) {
            productIdAndQuantity.put(item.getProductId(), item.getQuantity());
        }
    }


}
