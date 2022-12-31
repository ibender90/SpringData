package ru.geek.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.geek.data.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceGreaterThan(Double minPrice);

    List<Product> findByPriceLessThan(Double maxPrice);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
}
