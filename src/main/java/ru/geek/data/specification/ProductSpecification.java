package ru.geek.data.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.geek.data.model.Product;

public class ProductSpecification {
    //почему мы не создаём бин этого класса?
    public static Specification<Product> priceGreaterOrEqualsThan(Double price){
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> priceLessOrEqualsThan(Double price){
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> nameLike(String namePart){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", namePart));
    }
}
