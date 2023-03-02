package ru.geek.market.core.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geek.market.api.DTO.CartDto;
import ru.geek.market.api.DTO.CartItemDto;
import ru.geek.market.core.integration.CartServiceIntegration;
import ru.geek.market.core.model.Order;
import ru.geek.market.core.model.OrderItem;

import ru.geek.market.core.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void placeNewOrder(String username) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart(username);

        Order newOrder = new Order();
        newOrder.setUserName(username);
        newOrder.setTotalPriceFromCart(cartDto.getTotalPrice());
        newOrder.setItems(
                cartDto.getCartItems().stream().map(
                        cartItemDto -> new OrderItem(
                                productService.findProductById(cartItemDto.getProductId()).get(),
                                cartItemDto.getProductPrice(),
                                cartItemDto.getQuantity(),
                                cartItemDto.getPriceCalculated(),
                                newOrder
                        )
                ).collect(Collectors.toList()));

        orderRepository.save(newOrder);

        cartServiceIntegration.ClearCart(username);
        //return order
    }
}
