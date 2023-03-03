package ru.geek.market.cart.test;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.geek.market.api.DTO.CartDto;
import ru.geek.market.api.DTO.CartItemDto;
import ru.geek.market.cart.converters.CartConverter;
import ru.geek.market.cart.model.Cart;
import ru.geek.market.cart.model.CartItem;
import ru.geek.market.cart.service.CartService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
public class CartControllerTest {
//    @Autowired
//    MockMvc mvc;
//    @MockBean
//    CartService cartService;
//    @MockBean
//    CartConverter cartConverter;
//
//    @Test
//    void getCurrentCartTest() throws Exception {
//        Cart cart = new Cart(new ArrayList<CartItem>(), BigDecimal.ZERO);
//        Mockito.when(cartService.getCurrentCart()).thenReturn(cart);
//        CartDto cartDto = new CartDto();
//        cartDto.setTotalPrice(cart.getTotalPrice());
//        cartDto.setCartItems(new ArrayList<CartItemDto>());
//        Mockito.when(cartConverter.entityToDto(cart)).thenReturn(cartDto);
//
//        mvc.perform(
//                        get("/api/v1/carts")
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.totalPrice").value(0.0))
//                .andExpect(jsonPath("$.cartItems").isArray())
//                .andExpect(jsonPath("$.cartItems", hasSize(0)));
//    }
}
