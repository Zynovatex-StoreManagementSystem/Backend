package com.example.L2.S2.Project.service;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.L2.S2.Project.model.Category;
import com.example.L2.S2.Project.repository.CategoryRepository;



@Service
@AllArgsConstructor
public class CategoryService {
    CategoryRepository categoryRepository;
    private Map<Long, Category> categoryCache = new HashMap<>();

    public Category getProductById(Long id) {
        if (!categoryCache.containsKey(id)) {
            Category category = categoryRepository.findById(id).orElse(null);
            if (category == null) {
                return null;
            }
            categoryCache.put(id, category);
        }
        return categoryCache.get(id);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
