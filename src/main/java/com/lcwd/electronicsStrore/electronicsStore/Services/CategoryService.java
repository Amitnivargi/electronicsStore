package com.lcwd.electronicsStrore.electronicsStore.Services;

import com.lcwd.electronicsStrore.electronicsStore.DTO.CategoryDto;

public interface CategoryService {
    CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto, String categoryId);

    void delete(String categoryId);

    CategoryDto get(String categoryId);
}
