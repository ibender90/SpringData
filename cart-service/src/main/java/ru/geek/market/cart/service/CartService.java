package ru.geek.market.cart.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geek.market.api.DTO.ProductDto;
import ru.geek.market.api.DTO.ResourceNotFoundException;
import ru.geek.market.cart.integrations.ProductServiceIntegration;
import ru.geek.market.cart.model.Cart;


@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart cart;

    @PostConstruct
    public void initCart(){
        cart = new Cart();
    }

    public Cart getCurrentCart(){
        return cart;
    }

    public void add(Long productID) {
        ProductDto product = productServiceIntegration.detProductByID(productID)
                .orElseThrow(()->new ResourceNotFoundException("Can not add product to cart, product not found in the database"));
        cart.addProductToCart(product);
    }

    public void increaseProductQuantity(Long id) {
        cart.increaseProductQuantity(id);
    }

    public void decreaseProductQuantity(Long id) {
        cart.decreaseProductQuantity(id);
    }

    public void removeProduct(Long id) {
        cart.removeProduct(id);
    }

//    public void deleteProductById(Long id) {
//        cart.getProductDTOList().removeIf(p -> p.getId().equals(id));
//    }
}
