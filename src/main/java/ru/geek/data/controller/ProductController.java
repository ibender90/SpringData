package ru.geek.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.geek.data.model.Product;
import ru.geek.data.service.ProductService;

import java.rmi.NoSuchObjectException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/price-above/{minPrice}")
    public List<Product> getProductsWithPriceAboveGiven(@PathVariable Double minPrice) {
        return productService.getProductsWithPriceAboveGiven(minPrice);
    }

    @GetMapping("/price-below/{maxPrice}")
    public List<Product> getProductsWithPriceBelowGiven(@PathVariable Double maxPrice) {
        return productService.getProductsWithPriceBelowGiven(maxPrice);
    }

    @GetMapping("/price-between")
    public List<Product> getProductsWithPriceBetween(@RequestParam Double minPrice, Double maxPrice) {
        // через Postman посылал параметры minPrice & maxPrice
        return productService.getProductsWithPriceBetween(minPrice, maxPrice);
    }


    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findProductById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PostMapping()
    public void saveNewProduct(@RequestBody Product product) {
        productService.save(product);
    }
}
