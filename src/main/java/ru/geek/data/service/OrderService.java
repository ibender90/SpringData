package ru.geek.data.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geek.data.model.Cart;
import ru.geek.data.model.Order;
import ru.geek.data.repository.OrderRepository;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public void placeNewOrder(Cart cart, Long userId) {
        Order newOrder = new Order(cart, userId);
        //можно достать из Order hash map c номером продукта и его количеством и сохранять по одному в базу, но мне кажется это не оптимальный подход
        orderRepository.save(newOrder);
    }


}
