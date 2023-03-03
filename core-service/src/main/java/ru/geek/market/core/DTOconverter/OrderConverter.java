package ru.geek.market.core.DTOconverter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geek.market.api.DTO.OrderDto;
import ru.geek.market.core.model.Order;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUserName(order.getUserName());
        orderDto.setTotalPriceFromCart(order.getTotalPriceFromCart());
        orderDto.setItems(order.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()));
        return orderDto;
    }
}
