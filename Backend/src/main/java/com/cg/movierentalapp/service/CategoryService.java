package com.cg.movierentalapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.movierentalapp.enitites.Category;
import com.cg.movierentalapp.repositories.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
