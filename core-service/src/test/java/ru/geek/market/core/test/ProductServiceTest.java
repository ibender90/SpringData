package ru.geek.market.core.test;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import ru.geek.market.api.DTO.ProductDto;
import ru.geek.market.core.model.Category;
import ru.geek.market.core.model.Product;
import ru.geek.market.core.repository.ProductRepository;
import ru.geek.market.core.service.CategoryService;
import ru.geek.market.core.service.ProductService;
import ru.geek.market.core.specification.ProductSpecification;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.mock;

//package name is important!
@SpringBootTest(classes = ProductService.class) //only one bean is needed from the context
public class ProductServiceTest {

    @Autowired
    ProductService productService;
    @MockBean
    ProductRepository productRepository;
    @MockBean
    CategoryService categoryService;

    private final Category category = new Category(1L, "Other vegetables", null);
    private final ProductDto productDto = new ProductDto(1L, "Vegetable", BigDecimal.valueOf(1.00), "Other vegetables");
    private final Product product = new Product(1L, "Vegetable", BigDecimal.valueOf(1.00), category);

    @Test
    void testTest() {
        Assertions.assertEquals(1, 1);
    }

    @Test
    void createNewProductTest() {
        Mockito.doReturn(Optional.of(category))
                .when(categoryService)
                .findByTitle("Other vegetables");

        Product expectedProduct = new Product();
        expectedProduct.setName(productDto.getName());
        expectedProduct.setCategory(category);
        expectedProduct.setPrice(productDto.getPrice());

        Product wrongProduct = new Product();
        wrongProduct.setName("Banana");
        wrongProduct.setCategory(null);
        wrongProduct.setPrice(BigDecimal.valueOf(0.00));

        Assertions.assertEquals(productService.createNewProduct(productDto), expectedProduct);
        Assertions.assertNotEquals(productService.createNewProduct(productDto), wrongProduct);
        Mockito.verify(productRepository, Mockito.times(2)).save(ArgumentMatchers.isA(Product.class));
    }

    @Test
    void updateProductTest() {

        Mockito.doReturn(Optional.of(product)).when(productRepository).findById(1L);

        ProductDto productToUpdateDto = new ProductDto(1L, "Fresh vegetable", BigDecimal.valueOf(2.00), "Other vegetables");
        Product updatedProduct =  productService.updateProduct(productToUpdateDto);

        Assertions.assertEquals(productToUpdateDto.getId(), updatedProduct.getId());
        Assertions.assertEquals(productToUpdateDto.getName(), updatedProduct.getName());
        Assertions.assertEquals(productToUpdateDto.getPrice(), updatedProduct.getPrice());
        Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(productToUpdateDto.getId()));
    }


    void findProductTest(){ //todo ask how

    }
}
