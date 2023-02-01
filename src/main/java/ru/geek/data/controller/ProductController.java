package ru.geek.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.geek.data.DTO.ProductDTO;
import ru.geek.data.DTOconverter.ProductDTOconverter;
import ru.geek.data.annotation.LogExecutionTime;
import ru.geek.data.exceptions.AppError;
import ru.geek.data.exceptions.ResourceNotFoundException;
import ru.geek.data.model.Product;
import ru.geek.data.service.ProductService;
import ru.geek.data.validator.ProductValidator;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductDTOconverter productDTOconverter;

    private final ProductValidator productValidator;

    @GetMapping
    public Page<ProductDTO> getAllProducts(
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
    public ProductDTO getProductByID(@PathVariable Long id) {
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
    public ProductDTO saveNewProduct(@RequestBody ProductDTO productDTO) {
        productValidator.validate(productDTO);
        Product newProduct = productDTOconverter.convertDTOtoProductEntity(productDTO);
        Product savedProduct = productService.save(newProduct);
        return productDTOconverter.covertProductEntityToDTO(savedProduct);
    }

    @PutMapping()
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        productValidator.validate(productDTO);
        Product updatedProduct = productService.update(productDTO);
        return productDTOconverter.covertProductEntityToDTO(updatedProduct);
    }
}
