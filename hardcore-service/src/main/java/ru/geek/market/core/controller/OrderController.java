package ru.geek.market.core.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.geek.market.core.model.User;
import ru.geek.market.core.service.OrderService;
import ru.geek.market.core.service.UserService;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(Principal principal){
        User user = userService.findByEmail(principal.getName()).orElseThrow(()->new RuntimeException("User with given email not found"));
        orderService.placeNewOrder(user);
    }

}
