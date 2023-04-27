package ru.geek.market.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(ProductBuilder productBuilder){
        this.name = productBuilder.name;
        this.price = productBuilder.price;
        this.category = productBuilder.category;
    }

    public static class ProductBuilder{
        private String name;
        private BigDecimal price;
        private Category category;

        public ProductBuilder() {
        }
        public ProductBuilder name(String name){
            this.name = name;
            return this;
        }

        public ProductBuilder price(BigDecimal price){
            this.price = price;
            return this;
        }

        public ProductBuilder category(Category category){
            this.category = category;
            return this;
        }

        public Product build(){
            return new Product(this);
        }
    }
}
