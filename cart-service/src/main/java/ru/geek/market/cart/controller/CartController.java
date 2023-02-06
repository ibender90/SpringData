package ru.geek.market.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geek.market.api.DTO.CartDto;
import ru.geek.market.cart.converters.CartConverter;
import ru.geek.market.cart.model.Cart;
import ru.geek.market.cart.service.CartService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@CrossOrigin("*") //todo security
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping
    public CartDto getCurrentCart(){
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }

    @GetMapping("/add/{product_id}")
    public void addProductToCart(@PathVariable Long product_id){
        cartService.add(product_id);
    }

    @GetMapping("/increase/{product_id}")
    public void increaseProductQuantity(@PathVariable Long product_id){
        cartService.increaseProductQuantity(product_id);
    }

    @GetMapping("/reduce/{product_id}")
    public void decreaseProductQuantity(@PathVariable Long product_id){
        cartService.decreaseProductQuantity(product_id);
    }

    @DeleteMapping("/remove/{product_id}")
    public void removeProductFromCart(@PathVariable Long product_id){
        cartService.removeProduct(product_id);
    }

}
