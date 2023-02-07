package ru.geek.market.core.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geek.market.api.DTO.CartDto;
import ru.geek.market.api.DTO.CartItemDto;
import ru.geek.market.core.model.Order;
import ru.geek.market.core.model.OrderItem;
import ru.geek.market.core.model.User;
import ru.geek.market.core.repository.OrderRepository;
import ru.geek.market.core.repository.ProductRepository;
import ru.geek.market.core.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;

    @Transactional
    public void placeNewOrder(User user) {
        CartDto cartDto = new CartDto(); //cartServiceIntegration.getCurrentCart()

        CartItemDto ci = new CartItemDto();
        ci.setProductId(1L);
        ci.setQuantity(1);
        ci.setProductPrice(1.0);

        List<CartItemDto> itemsInCart = new ArrayList<>();
        itemsInCart.add(ci);

        cartDto.setCartItems(itemsInCart);
        cartDto.setTotalPrice(1.0);

        Order newOrder = new Order();
        newOrder.setUser(user); //todo fix hardcode
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

        //cartServiceIntegration.ClearCart();
    }
}
