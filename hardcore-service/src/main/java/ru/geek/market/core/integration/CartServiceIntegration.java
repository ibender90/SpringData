package ru.geek.market.core.integration;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geek.market.api.DTO.CartDto;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;
    @Value("${cartServiceIntegration.endpoint}")
    private String cartsEndpointURL;

    public CartDto getCurrentCart(){
        return restTemplate.getForObject(
                cartsEndpointURL, CartDto.class
        );
    }

    public void ClearCart(){ //todo proper method
        restTemplate.delete(cartsEndpointURL+ "/1");
    }
}
