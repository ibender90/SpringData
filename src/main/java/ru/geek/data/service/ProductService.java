package ru.geek.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geek.data.model.Product;
import ru.geek.data.repository.ProductRepository;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll(); // не лучше ли тут List<Optional> ? вдруг не будет в базе ничего
    }

    @Transactional
    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsWithPriceAboveGiven(Double minPrice) {
        return productRepository.findByPriceGreaterThan(minPrice);
    }

    public List<Product> getProductsWithPriceBelowGiven(Double maxPrice) {
        return productRepository.findByPriceLessThan(maxPrice);
    }

    public List<Product> getProductsWithPriceBetween(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
