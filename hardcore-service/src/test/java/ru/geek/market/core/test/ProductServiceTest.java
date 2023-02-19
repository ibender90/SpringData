package ru.geek.market.core.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geek.market.api.DTO.ProductDto;
import ru.geek.market.core.model.Category;
import ru.geek.market.core.model.Product;
import ru.geek.market.core.repository.ProductRepository;
import ru.geek.market.core.service.CategoryService;
import ru.geek.market.core.service.ProductService;

import java.util.Optional;

//package name is important!
@SpringBootTest(classes = ProductService.class) //only one bean is needed from the context
public class ProductServiceTest {
    //todo test all methods

    @Autowired
    ProductService productService;
    @MockBean
    ProductRepository productRepository;
    @MockBean
    CategoryService categoryService;

    private final Category category = new Category(1L, "Other vegetables", null);
    private final ProductDto productDto = new ProductDto(1L, "Vegetable", 1.0D, "Other vegetables");
    private final Product product = new Product(1L, "Vegetable", 1.0D, category);

    @Test
    void testTest() {
        Assertions.assertEquals(1, 1);
    }

    @Test
    void testCreateNewProduct() {


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
        wrongProduct.setPrice(0.0);

        Assertions.assertEquals(productService.createNewProduct(productDto), expectedProduct);
        Assertions.assertNotEquals(productService.createNewProduct(productDto), wrongProduct);
        Mockito.verify(productRepository, Mockito.times(2)).save(ArgumentMatchers.isA(Product.class));
    }

    @Test
    void updateProductTest() {

        Mockito.doReturn(Optional.of(product)).when(productRepository).findById(1L);

        ProductDto productToUpdateDto = new ProductDto(1L, "Fresh vegetable", 2.0D, "Other vegetables");

        Product updatedProduct =  productService.update(productToUpdateDto);

        Assertions.assertEquals(productToUpdateDto.getId(), updatedProduct.getId());
        Assertions.assertEquals(productToUpdateDto.getName(), updatedProduct.getName());
        Assertions.assertEquals(productToUpdateDto.getPrice(), updatedProduct.getPrice());
        Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(productToUpdateDto.getId()));
    }
}
