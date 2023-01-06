package ru.geek.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geek.data.model.Product;
import ru.geek.data.repository.ProductRepository;
import ru.geek.data.specification.ProductSpecification;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private static final int PRODUCTS_PER_PAGE = 5;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> find(Double minPrice, Double maxPrice, String namePart, Integer page){
        Specification<Product> spec = Specification.where(null);
        //todo
        //select p from Products p where true ??? что
        if(minPrice != null){
            spec = spec.and(ProductSpecification.priceGreaterOrEqualsThan(minPrice));
            //select p from Products p where true AND p.price > minPrice
        }
        if(maxPrice != null){
            spec = spec.and(ProductSpecification.priceLessOrEqualsThan(maxPrice));
            //select p from Products p where true AND p.price > minPrice AND p.rice < maxPrice
        }
        if(namePart != null){
            spec = spec.and(ProductSpecification.nameLike(namePart));
            //select p from Products p where true AND p.price > minPrice AND p.rice < maxPrice AND p.name LIKE %namePart
        }
        return productRepository.findAll(spec, PageRequest.of(page-1, PRODUCTS_PER_PAGE));
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll(); // не лучше ли тут List<Optional> ? вдруг не будет в базе ничего
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

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
