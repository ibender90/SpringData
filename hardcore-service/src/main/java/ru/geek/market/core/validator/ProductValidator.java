package ru.geek.market.core.validator;

import org.springframework.stereotype.Component;
import ru.geek.market.api.DTO.ProductDto;
import ru.geek.market.core.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDTO){
        List<String> errorsList = new ArrayList<>();
        if(productDTO.getPrice() < 0.01){
            errorsList.add("Product price can not be set to negative value");
        }
        if(productDTO.getName().isBlank()){
            errorsList.add("Product name can not be empty");
        }
        if(!errorsList.isEmpty()){
            throw new ValidationException(errorsList);
        }
    }
}
