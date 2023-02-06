package ru.geek.market.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geek.market.core.model.Category;
import ru.geek.market.core.repository.CategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title){
        return categoryRepository.findByTitle(title);
    }
}
