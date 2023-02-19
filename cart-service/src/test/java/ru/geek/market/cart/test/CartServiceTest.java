package ru.geek.market.cart.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geek.market.api.DTO.ProductDto;
import ru.geek.market.cart.integrations.ProductServiceIntegration;
import ru.geek.market.cart.model.Cart;
import ru.geek.market.cart.model.CartItem;
import ru.geek.market.cart.service.CartService;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = CartService.class)
class CartServiceTest extends Cart {
    @Autowired
    CartService cartService;

    @MockBean
    ProductServiceIntegration productServiceIntegration;


    @Test
    void getCurrentCartTest() {
        Assertions.assertNotNull(cartService.getCurrentCart());
    }

    @Test
    void addProductToCartTest() {
        ProductDto potatoDto = new ProductDto(1L, "Potato", 1.2, "Other vegetables");
        Mockito.when(productServiceIntegration.findById(1L)).thenReturn(potatoDto);
        CartItem potatoItem = new CartItem(1L, "Potato", 1.2, 1, 1.2);

        cartService.addProductToCart(1L);
        Assertions.assertEquals(potatoItem, cartService.getCurrentCart().getItems().get(0));

        ProductDto onionDto = new ProductDto(3L, "Onion", 1.1, "Other vegetables");
        CartItem onionItem = new CartItem(3L, "Onion", 1.1, 1, 1.1);
        Mockito.when(productServiceIntegration.findById(3L)).thenReturn(onionDto);

        cartService.addProductToCart(3L);
        Assertions.assertEquals(onionItem, cartService.getCurrentCart().getItems().get(1));
        Assertions.assertEquals(2, cartService.getCurrentCart().getItems().size());
    }
}