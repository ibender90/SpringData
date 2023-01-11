package ru.geek.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geek.data.DTO.ProductDTO;
import ru.geek.data.model.Product;
import ru.geek.data.service.CartService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/added_to_cart_products")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return cartService.getAllProducts();
    }

    @PostMapping
    public ProductDTO addProductToCart(@RequestBody ProductDTO productDTO) {
        cartService.add(productDTO);
        return productDTO;
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        cartService.deleteProductById(id);
    }
}
