package ru.geek.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.geek.data.DTO.ProductDTO;
import ru.geek.data.model.Product;
import ru.geek.data.service.ProductService;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDTO> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Double minPrice,
            @RequestParam(name = "max_price", required = false) Double maxPrice,
            @RequestParam(name = "name_part", required = false) String namePart
    ) {
        if(page < 1){
            page = 1;
        }
        return productService.find(minPrice, maxPrice, namePart, page).map(
                s-> new ProductDTO(s)
        );
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return new ProductDTO(productService.findProductById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PostMapping()
    public ProductDTO saveNewProduct(@RequestBody ProductDTO productDTO) {
        Product newProduct = new Product();
        newProduct.setName(productDTO.getName());
        newProduct.setPrice(productDTO.getPrice());
        return new ProductDTO(productService.save(newProduct));
    }

    @PutMapping()
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO){
        Product productToUpdate = new Product();
        productToUpdate.setId(productDTO.getId());
        productToUpdate.setName(productDTO.getName());
        productToUpdate.setPrice(productDTO.getPrice());
        return new ProductDTO(productService.save(productToUpdate));
    }
}
