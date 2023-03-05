package com.lcwd.electronicsStrore.electronicsStore.Services.impl;

import com.lcwd.electronicsStrore.electronicsStore.DTO.CategoryDto;
import com.lcwd.electronicsStrore.electronicsStore.Entities.Category;
import com.lcwd.electronicsStrore.electronicsStore.Exceptions.ResourceNotFoundException;
import com.lcwd.electronicsStrore.electronicsStore.Services.CategoryService;
import com.lcwd.electronicsStrore.electronicsStore.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public CategoryDto create(CategoryDto categoryDto) {

        // creating categoryId randomally
        String categoryId = UUID.randomUUID().toString();
        categoryDto.setCategoryId(categoryId);

        Category category = mapper.map(categoryDto, Category.class);
        Category savedcategory = categoryRepository.save(category);

        return mapper.map(savedcategory, CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category save = categoryRepository.save(category);
        return mapper.map(save, CategoryDto.class);

    }

    @Override
    public void delete(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto get(String categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return mapper.map(category, CategoryDto.class);
    }
}
