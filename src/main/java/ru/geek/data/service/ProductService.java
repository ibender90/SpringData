package ru.geek.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geek.data.DTO.ProductDTO;
import ru.geek.data.exceptions.ResourceNotFoundException;
import ru.geek.data.model.Product;
import ru.geek.data.repository.ProductRepository;
import ru.geek.data.specification.ProductSpecification;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private static final int PRODUCTS_PER_PAGE = 5;

    public Page<Product> find(Double minPrice, Double maxPrice, String namePart, Integer page) {
        Specification<Product> spec = Specification.where(null);
        //todo
        //select p from Products p where true ??? что
        if (minPrice != null) {
            spec = spec.and(ProductSpecification.priceGreaterOrEqualsThan(minPrice));
            //select p from Products p where true AND p.price > minPrice
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecification.priceLessOrEqualsThan(maxPrice));
            //select p from Products p where true AND p.price > minPrice AND p.rice < maxPrice
        }
        if (namePart != null) {
            spec = spec.and(ProductSpecification.nameLike(namePart));
            //select p from Products p where true AND p.price > minPrice AND p.rice < maxPrice AND p.name LIKE %namePart
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, PRODUCTS_PER_PAGE));
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll(); // не лучше ли тут List<Optional> ? вдруг не будет в базе ничего
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product update(ProductDTO updatedProductDTO) {
        Product productEntity = productRepository.findById(updatedProductDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Product with given id not found"));
        productEntity.setPrice(updatedProductDTO.getPrice());
        productEntity.setName(updatedProductDTO.getName());
        return productEntity;
    }
}
