package ru.geek.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geek.data.DTO.ProductDTO;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Component
@Data
public class Cart {
    private List<ProductDTO> productDTOList = new ArrayList<>();;

    public void addProductToCart(ProductDTO productDTO){ //пока вместо репозитория
        productDTOList.add(productDTO);
    }
}
