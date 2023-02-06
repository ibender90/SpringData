package ru.geek.market.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geek.market.core.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
