package ru.geek.market.core.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.geek.market.api.DTO.CartDto;
import ru.geek.market.api.DTO.ResourceNotFoundException;


@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public CartDto getCurrentCart(String username) {
        return cartServiceWebClient.get()
                .uri("/api/v1/carts/0")
                .header("username", username)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        clientResponse -> Mono.error(new ResourceNotFoundException("Cart ms failed to return current cart"))
                )
                .bodyToMono(CartDto.class)
                .block();
    }

    public void ClearCart(String username){
        cartServiceWebClient.delete()
                .uri("/api/v1/carts/0")
                .header("username", username)
                .retrieve()
                .toBodilessEntity();
    }
    //username will be taken from token /0 means that uuid is not needed
}
