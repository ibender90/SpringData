package ru.geek.market.cart.converters;

import org.springframework.stereotype.Component;
import ru.geek.market.api.DTO.CartItemDto;
import ru.geek.market.cart.model.CartItem;

@Component
public class CartItemConverter {
    public CartItemDto entityToDto(CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setProductName(cartItem.getProductName());
        cartItemDto.setProductPrice(cartItem.getProductPrice());
        cartItemDto.setPriceCalculated(cartItem.getProductPrice());
        cartItemDto.setQuantity(cartItem.getQuantity());
        return cartItemDto;
    }
}
