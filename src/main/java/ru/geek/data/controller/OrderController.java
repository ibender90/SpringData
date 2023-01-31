package ru.geek.data.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geek.data.model.Cart;
import ru.geek.data.service.OrderService;
@Slf4j
@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/new")
    public String placeNewOrder(@RequestBody Cart cart) {
        log.info(cart.toString());
        Long userID = 1L; //todo добавить извлечение id пользователя из токена, наверное, не знаю
        orderService.placeNewOrder(cart, userID);
        log.info(cart.toString());
        return "Order was placed, processing...";
    }
}
