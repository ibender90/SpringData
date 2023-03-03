package ru.geek.market.core.DTOconverter;

import org.springframework.stereotype.Component;
import ru.geek.market.api.DTO.ProductDto;
import ru.geek.market.core.model.Product;

@Component
public class ProductConverter {

//    public Product convertDTOtoProductEntity(ProductDTO productDTOToConvert){
//        return new Product(productDTOToConvert.getId(), productDTOToConvert.getName(), productDTOToConvert.getPrice());
//    } //not in use for now

    public ProductDto covertProductEntityToDTO(Product productEntityToConvert){
        return new ProductDto(productEntityToConvert.getId(), productEntityToConvert.getName(), productEntityToConvert.getPrice(), productEntityToConvert.getCategory().getTitle());
    }
}
