package ru.geek.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geek.data.DTO.ProductDTO;
import ru.geek.data.model.Cart;
import ru.geek.data.model.Product;
import ru.geek.data.service.CartService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Cart getCurrentCart(){
        return cartService.getCurrentCart();
    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id){
        cartService.add(id);
    }

    @GetMapping("/increase/{id}")
    public void increaseProductQuantity(@PathVariable Long id){
        cartService.increaseProductQuantity(id);
    }

    @GetMapping("/reduce/{id}")
    public void decreaseProductQuantity(@PathVariable Long id){
        cartService.decreaseProductQuantity(id);
    }

    @DeleteMapping("/remove/{id}")
    public void removeProductFromCart(@PathVariable Long id){
        cartService.removeProduct(id);
    }

}
