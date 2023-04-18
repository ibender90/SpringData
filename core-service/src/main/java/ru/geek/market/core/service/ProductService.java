package ru.geek.market.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geek.market.api.DTO.ProductDto;
import ru.geek.market.api.DTO.ResourceNotFoundException;
import ru.geek.market.core.annotation.LogExecutionTime;
import ru.geek.market.core.model.Category;
import ru.geek.market.core.model.Product;
import ru.geek.market.core.repository.ProductRepository;
import ru.geek.market.core.specification.ProductSpecification;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@LogExecutionTime
public class ProductService {

    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    private static final int PRODUCTS_PER_PAGE = 5;

    public Page<Product> find(Double minPrice, Double maxPrice, String namePart, Integer page) {
        Specification<Product> spec = Specification.where(null);

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
    public Product updateProduct(ProductDto updatedProductDTO) {
        Product productEntity = productRepository.findById(updatedProductDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Product with given id not found"));
        productEntity.setPrice(updatedProductDTO.getPrice());
        productEntity.setName(updatedProductDTO.getName());
        return productEntity;
    }

    @Transactional
    public Product createNewProduct(ProductDto productDTO) {
        Product product = new Product.ProductBuilder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .category(categoryService.findByTitle(productDTO.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category with such title not found")))
                .build();
//        old implementation without builder
//        Product product = new Product();
//        product.setPrice(productDTO.getPrice());
//        product.setName(productDTO.getName());
//        Category category = categoryService.findByTitle(productDTO.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category with such title not found"));
//        product.setCategory(category);
        productRepository.save(product);
        return product;
    }
}
