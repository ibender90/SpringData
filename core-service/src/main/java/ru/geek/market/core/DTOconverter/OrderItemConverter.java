package ru.geek.market.core.DTOconverter;

import org.springframework.stereotype.Component;
import ru.geek.market.api.DTO.OrderItemDto;
import ru.geek.market.core.model.OrderItem;
@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem orderItem){
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setQuantity(orderItemDto.getQuantity());
        orderItemDto.setTotalPrice(orderItem.getTotalPrice());
        orderItemDto.setProductName(orderItem.getProduct().getName());
        orderItemDto.setPricePerProduct(orderItem.getPricePerProduct());
        return orderItemDto;
    }
}
