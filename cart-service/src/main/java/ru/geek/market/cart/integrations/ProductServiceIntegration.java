package ru.geek.market.cart.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geek.market.api.DTO.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<ProductDto> detProductByID(Long id){ //add dependency on module api to get ProductDto model
        ProductDto productDto = restTemplate.getForObject(
                "http://localhost:8181/market/api/v1/products/" + id, ProductDto.class
        );                                                                 //ProductDto is expected in response
        return Optional.ofNullable(productDto); //todo handle exceptions
    }

}
