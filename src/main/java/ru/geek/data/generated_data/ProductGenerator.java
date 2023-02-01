package ru.geek.data.generated_data;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.geek.data.annotation.LogExecutionTime;
import ru.geek.data.model.Product;
import ru.geek.data.repository.ProductRepository;


@Component
@Slf4j
@LogExecutionTime
public class ProductGenerator {
    private ProductRepository productRepository;

    private static final int PRODUCTS_TO_GENERATE = 20;

    private static final int SCALE = 100;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void generateDataForDataBase() {
        Faker faker = new Faker();
        for (int i = 0; i < PRODUCTS_TO_GENERATE; i++) {
            Product product = new Product();
            product.setName(faker.food().vegetable());
            product.setPrice(Math.ceil((faker.random().nextDouble()) * SCALE) / SCALE);

            productRepository.save(product);
        }
        log.info("Products generated successfully");
    }
}
