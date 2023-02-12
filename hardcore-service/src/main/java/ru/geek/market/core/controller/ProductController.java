package ru.geek.market.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.geek.market.api.DTO.ProductDto;
import ru.geek.market.api.DTO.ResourceNotFoundException;
import ru.geek.market.core.DTOconverter.ProductDTOconverter;
import ru.geek.market.core.model.Product;
import ru.geek.market.core.service.ProductService;
import ru.geek.market.core.validator.ProductValidator;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/products")
@CrossOrigin(origins = "http://localhost:8180")
public class ProductController {
    private final ProductService productService;
    private final ProductDTOconverter productDTOconverter;

    private final ProductValidator productValidator;

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Double minPrice,
            @RequestParam(name = "max_price", required = false) Double maxPrice,
            @RequestParam(name = "name_part", required = false) String namePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(minPrice, maxPrice, namePart, page).map(
                productDTOconverter::covertProductEntityToDTO
        );
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getProductById(@PathVariable Long id) {
//
//        Optional<Product> product = productService.findProductById(id);
//
//        if (product.isEmpty()) { // если продукта в контейнере нет
//            return new ResponseEntity<AppError>(
//                    new AppError(HttpStatus.NOT_FOUND.value(), "Product not found"), HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<ProductDTO>( //если продукт есть, конвертирую в DTO и отсылаю как response entity
//                productDTOconverter.covertProductEntityToDTO(product.get()), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ProductDto getProductByID(@PathVariable Long id) {
        return productDTOconverter.covertProductEntityToDTO(
                productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException( //Global exception handler перехватит эту ошибку и упакует в Response entity
                        "Product not found, id: " + id)
                ));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PostMapping()
    public ProductDto saveNewProduct(@RequestBody ProductDto productDTO) {
        productValidator.validate(productDTO);
        productDTO.setCategoryTitle("Other vegetables"); //todo choose category field
        Product newProduct = productService.createNewProduct(productDTO);
        return productDTOconverter.covertProductEntityToDTO(newProduct);
    }

    @PutMapping()
    public ProductDto updateProduct(@RequestBody ProductDto productDTO) {
        productValidator.validate(productDTO);
        Product updatedProduct = productService.update(productDTO);
        return productDTOconverter.covertProductEntityToDTO(updatedProduct);
    }
}
