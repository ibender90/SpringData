package ru.geek.market.core.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geek.market.api.DTO.ProductDto;
import ru.geek.market.core.generated_data.ProductGenerator;
import ru.geek.market.core.model.Category;
import ru.geek.market.core.model.Product;
import ru.geek.market.core.repository.ProductRepository;
@Slf4j
@SpringBootTest(classes = ProductGenerator.class)
public class ProductGeneratorTest {
    @Autowired
    ProductGenerator productGenerator;

    @MockBean
    ProductRepository productRepository;

    @Test
    void generateDataForDataBaseTest(){
        Answer<Product> generatedProduct = invocationOnMock -> {
            Product generatedByFaker = invocationOnMock.getArgument(0, Product.class);
            log.info(generatedByFaker.toString());
            return generatedByFaker;
        };
        Mockito.when(productRepository.save(ArgumentMatchers.isA(Product.class)))
                .then(generatedProduct);
        productGenerator.generateDataForDataBase();

        //Mockito.verify(productRepository, Mockito.times(20)).save(ArgumentMatchers.isA(Product.class));
        //     @EventListener(ApplicationReadyEvent.class) to test .save method calls comment out this annotation
    }
}
