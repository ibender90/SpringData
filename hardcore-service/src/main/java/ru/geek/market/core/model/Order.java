package ru.geek.market.core.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geek.market.api.DTO.CartDto;

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
    @Column(name = "total_price_from_cart")
    private Double totalPriceFromCart;

    @Column(name = "items")
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItem> items;

    @Column(name = "user_name")
    private String userName;

}
