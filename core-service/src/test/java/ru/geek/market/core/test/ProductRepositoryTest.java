package ru.geek.market.core.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.geek.market.api.DTO.ProductDto;
import ru.geek.market.core.model.Category;
import ru.geek.market.core.model.Product;
import ru.geek.market.core.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test") //todo add active profiles file from lesson
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void productRepositoryTest() { //todo add test settings in properties
        Category category = new Category(1L, "Other vegetables", null);
        Product product = new Product(1L, "Vegetable", BigDecimal.valueOf(1.00), category);

        testEntityManager.persist(product);
        testEntityManager.flush();

        List<Product> productList = productRepository.findAll();
        Assertions.assertEquals(product, productList.get(0));
    }
}
