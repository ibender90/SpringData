package ru.geek.market.cart.integrations;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geek.market.api.DTO.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${productServiceIntegration.endpoint}")
    private String endpointURL;

    public Optional<ProductDto> detProductByID(Long id){ //add dependency on module api to get ProductDto model
        ProductDto productDto = restTemplate.getForObject(
                endpointURL + id, ProductDto.class
        );                                                                 //ProductDto is expected in response
        return Optional.ofNullable(productDto); //todo handle exceptions
    }

}
