package ru.geek.market.cart.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

//@ConstructorBinding deprecated
@ConfigurationProperties(prefix = "integrations.product-service") //application.yaml integrations -> product-service
@Data
public class ProductServiceIntegrationProperties {
    private String url;
    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;
}
