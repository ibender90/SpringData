package ru.geek.market.core.test;

import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.geek.market.core.specification.ProductSpecification;

@SpringBootTest(classes = ProductSpecification.class)
public class ProductSpecificationTest {

    void priceGreaterOrEqualsThanTest() { //todo
        try (MockedStatic<ProductSpecification> productSpec = Mockito.mockStatic(ProductSpecification.class)) {
            productSpec.when(() -> ProductSpecification.priceGreaterOrEqualsThan(10.0))
                    .thenReturn(null);
            //get request from criteria builder and compare to statement
            //"select p from Products p where true AND p.price > 10.0"
            //get mock objects from repository
        }
    }
}
