package ru.geek.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geek.data.model.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
