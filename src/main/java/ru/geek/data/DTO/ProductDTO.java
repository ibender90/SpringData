package ru.geek.data.DTO;

import lombok.Data;
import ru.geek.data.model.Product;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;

    public ProductDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public ProductDTO() {
    }
}
