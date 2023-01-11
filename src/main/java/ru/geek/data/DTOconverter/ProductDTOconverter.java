package ru.geek.data.DTOconverter;

import org.springframework.stereotype.Component;
import ru.geek.data.DTO.ProductDTO;
import ru.geek.data.model.Product;

@Component
public class ProductDTOconverter {

    public Product convertDTOtoProductEntity(ProductDTO productDTOToConvert){
        return new Product(productDTOToConvert.getId(), productDTOToConvert.getName(), productDTOToConvert.getPrice());
    }

    public ProductDTO covertProductEntityToDTO(Product productEntityToConvert){
        return new ProductDTO(productEntityToConvert.getId(), productEntityToConvert.getName(), productEntityToConvert.getPrice());
    }
}
