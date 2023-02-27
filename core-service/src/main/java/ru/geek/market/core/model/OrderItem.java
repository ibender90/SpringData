package ru.geek.market.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "price_per_product")
    private Double pricePerProduct;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    public OrderItem(Product product, Double pricePerProduct, Integer quantity, Double totalPrice, Order order) {
        this.product = product;
        this.pricePerProduct = pricePerProduct;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.order = order;
    }
}
