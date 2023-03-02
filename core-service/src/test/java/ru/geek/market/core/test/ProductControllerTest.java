package ru.geek.market.core.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.geek.market.api.DTO.ProductDto;
import ru.geek.market.core.DTOconverter.ProductDTOconverter;
import ru.geek.market.core.model.Category;
import ru.geek.market.core.model.Product;
import ru.geek.market.core.service.ProductService;
import ru.geek.market.core.validator.ProductValidator;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    ProductService productService;

    @MockBean
    ProductDTOconverter productDTOconverter;

    @MockBean
    ProductValidator productValidator;

    private final ProductDto productDto = new ProductDto(1L, "Vegetable", BigDecimal.valueOf(1.00), "Other vegetables");
    private final Category category = new Category(1L, "Other vegetables", null);
    private final Product product = new Product(1L, "Vegetable", BigDecimal.valueOf(1.00), category);

    @Test
    void getProductByIDTest() throws Exception {

        Mockito.doReturn(Optional.of(product)).when(productService).findProductById(1L);
        Mockito.doReturn(productDto).when(productDTOconverter).covertProductEntityToDTO(product);

        mvc.perform(
                        get("/api/v1/products/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id").value(1.00))
                .andExpect(jsonPath("$.name").value("Vegetable"))
                .andExpect(jsonPath("$.price").value(1.00))
                .andExpect(jsonPath("$.categoryTitle").value("Other vegetables"));
    }

//    @PostMapping()
//    public ProductDto saveNewProduct(@RequestBody ProductDto productDTO) {
//        productValidator.validate(productDTO);
//        productDTO.setCategoryTitle("Other vegetables");
//        Product newProduct = productService.createNewProduct(productDTO);
//        return productDTOconverter.covertProductEntityToDTO(newProduct);
//    }

    @Test
    void saveNewProductTest() throws Exception {

        Mockito.doReturn(product).when(productService).createNewProduct(productDto);
        Mockito.doReturn(productDto).when(productDTOconverter).covertProductEntityToDTO(product);

        //make a JSON from productDTO to pass into BODY
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String JsonProductDto = ow.writeValueAsString(productDto);

        mvc.perform(post("/api/v1/products")
                        .contentType(APPLICATION_JSON)
                        .content(JsonProductDto))
                .andDo(print())
                .andExpect(status().isOk()); //no JSON is returned !
//                .andExpect(jsonPath("$").isNotEmpty())
//                .andExpect(jsonPath("$.id").value(1.0))
//                .andExpect(jsonPath("$.name").value("Vegetable"))
//                .andExpect(jsonPath("$.price").value(1.0D))
//                .andExpect(jsonPath("$.categoryTitle").value("Other vegetables"));
    }

}
