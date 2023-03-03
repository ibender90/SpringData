package ru.geek.market.cart.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geek.market.api.DTO.CartDto;
import ru.geek.market.cart.model.Cart;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;

    public CartDto entityToDto(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(cart.getTotalPrice());
        cartDto.setCartItems(
                cart.getCartItems().stream().map(cartItemConverter::entityToDto).collect(Collectors.toList())
        );
        return cartDto;
    }
}
