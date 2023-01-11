package ru.geek.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geek.data.DTO.ProductDTO;
import ru.geek.data.model.Cart;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final Cart cart;

    public List<ProductDTO> getAllProducts() {
        return cart.getProductDTOList();
    }

    public void add(ProductDTO productDTO) {
        cart.addProductToCart(productDTO);
    }

    public void deleteProductById(Long id) {
        cart.getProductDTOList().removeIf(p -> p.getId().equals(id));
    }
}
