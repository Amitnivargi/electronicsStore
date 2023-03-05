package com.lcwd.electronicsStrore.electronicsStore.Services.impl;

import com.lcwd.electronicsStrore.electronicsStore.DTO.ProductDto;
import com.lcwd.electronicsStrore.electronicsStore.Entities.Category;
import com.lcwd.electronicsStrore.electronicsStore.Entities.Product;
import com.lcwd.electronicsStrore.electronicsStore.Exceptions.ResourceNotFoundException;
import com.lcwd.electronicsStrore.electronicsStore.Services.ProductService;
import com.lcwd.electronicsStrore.electronicsStore.repositories.CategoryRepository;
import com.lcwd.electronicsStrore.electronicsStore.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductDto create(ProductDto productDto) {

        Product product = modelMapper.map(productDto, Product.class);
        // generate pooductId
        String productId=UUID.randomUUID().toString();
        product.setProductId(productId);
        product.setAddedDate(new Date());
        Product save = productRepository.save(product);

        return modelMapper.map(save, ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found"));
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setQuantity(productDto.getQuantity());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());
        product.setProductImageName(productDto.getProductImageName());

        Product updatedProduct = productRepository.save(product);
        return modelMapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    public void delete(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found"));
        productRepository.delete(product);
    }

    @Override
    public ProductDto get(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found"));
        return modelMapper.map(product,ProductDto.class);
    }

    @Override
    public List<ProductDto> getAll() {
        return null;
    }

    @Override
    public List<ProductDto> searchByTitle(String subTitle) {
        return null;
    }

    @Override
    public ProductDto createWithCategory(ProductDto productDto, String categoryId) {
        //fetch  the category
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found with given id"));
        Product product = modelMapper.map(productDto, Product.class);
        // generate pooductId
        String productId=UUID.randomUUID().toString();
        product.setProductId(productId);
        product.setAddedDate(new Date());
        product.setCategory(category);
        Product save = productRepository.save(product);

        return modelMapper.map(save, ProductDto.class);
    }

    @Override
    public ProductDto updateCategory(String productId, String categoryId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product of given id not found!!"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found"));
        product.setCategory(category);
        Product save = productRepository.save(product);
        return modelMapper.map(save,ProductDto.class);
    }
}
