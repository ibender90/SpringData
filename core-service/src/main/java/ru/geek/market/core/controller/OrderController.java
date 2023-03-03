package ru.geek.market.core.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geek.market.api.DTO.OrderDto;
import ru.geek.market.core.DTOconverter.OrderConverter;
import ru.geek.market.core.service.OrderService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderConverter orderConverter;
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestHeader String username){
        orderService.placeNewOrder(username);
    }

    @GetMapping
    public List<OrderDto> getUsersOrders(@RequestHeader String username){
        return orderService.findByUsername(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
