package ru.geek.data.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geek.data.DTO.ProductDTO;
import ru.geek.data.exceptions.ResourceNotFoundException;
import ru.geek.data.model.Cart;
import ru.geek.data.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private Cart cart;

    @PostConstruct
    public void initCart(){
        cart = new Cart();
    }

    public Cart getCurrentCart(){
        return cart;
    }

    public void add(Long productID) {
        Product product = productService.findProductById(productID)
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
