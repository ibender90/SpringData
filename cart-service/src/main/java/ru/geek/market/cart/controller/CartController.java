package ru.geek.market.cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geek.market.api.DTO.CartDto;
import ru.geek.market.api.DTO.StringResponse;
import ru.geek.market.cart.converters.CartConverter;
import ru.geek.market.cart.service.CartService;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/generate_uuid")
    public StringResponse generateUuid(){
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(
            @RequestHeader(name = "username", required = false) String username,
            @PathVariable String uuid){

        String targetUuid = getCartUuid(username, uuid);
        return cartConverter.entityToDto(cartService.getCurrentCart(targetUuid));
    }

    @DeleteMapping("/{uuid}")
    public void clearCart(
            @RequestHeader(name = "username", required = false) String username,
            @PathVariable String uuid){

        String targetUuid = getCartUuid(username, uuid);
        cartService.clearCart(targetUuid);
    }

    @GetMapping("/{uuid}/add/{product_id}")
    public void addProductToCart(
            @RequestHeader(name = "username", required = false) String username,
            @PathVariable String uuid,
            @PathVariable Long product_id){

        String targetUuid = getCartUuid(username, uuid);
        cartService.addProductToCart(targetUuid, product_id);
    }

    @GetMapping("/{uuid}/increase/{product_id}")
    public void increaseProductQuantity(
            @RequestHeader(name = "username", required = false) String username,
            @PathVariable String uuid,
            @PathVariable Long product_id){

        String targetUuid = getCartUuid(username, uuid);
        cartService.increaseProductQuantity(targetUuid, product_id);
    }

    @GetMapping("/{uuid}/reduce/{product_id}")
    public void decreaseProductQuantity(
            @RequestHeader(name = "username", required = false) String username,
            @PathVariable String uuid,
            @PathVariable Long product_id){

        String targetUuid = getCartUuid(username, uuid);
        cartService.decreaseProductQuantity(targetUuid, product_id);
    }

    @DeleteMapping("/{uuid}/remove/{product_id}")
    public void removeProductFromCart(
            @RequestHeader(name = "username", required = false) String username,
            @PathVariable String uuid,
            @PathVariable Long product_id){

        String targetUuid = getCartUuid(username, uuid);
        cartService.removeProduct(targetUuid, product_id);
    }

    private String getCartUuid(String username, String uuid){
        if(username != null){
            return username;
        }
        return uuid;
    }

}
