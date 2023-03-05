package com.lcwd.electronicsStrore.electronicsStore.Services;

import com.lcwd.electronicsStrore.electronicsStore.DTO.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto create(ProductDto productDto);

    ProductDto update(ProductDto productDto,String productId);

    void delete(String productId);

    ProductDto get(String productId);

    List<ProductDto> getAll();

    List<ProductDto> searchByTitle(String subTitle);

    //create product with category
    ProductDto createWithCategory(ProductDto productDto,String categoryId);

    // update category of product
    ProductDto updateCategory(String productId,String categoryId);



}
