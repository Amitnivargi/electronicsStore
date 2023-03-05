package com.lcwd.electronicsStrore.electronicsStore.Controllers;

import com.lcwd.electronicsStrore.electronicsStore.DTO.ApiResponseMessege;
import com.lcwd.electronicsStrore.electronicsStore.DTO.CategoryDto;
import com.lcwd.electronicsStrore.electronicsStore.DTO.ProductDto;
import com.lcwd.electronicsStrore.electronicsStore.Services.CategoryService;
import com.lcwd.electronicsStrore.electronicsStore.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<CategoryDto> creteCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto categoryDto1 = categoryService.create(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable String categoryId, @RequestBody CategoryDto categoryDto) {
        CategoryDto updatedCategory = categoryService.update(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponseMessege> deleteCategory(@PathVariable String categoryId) {
        categoryService.delete(categoryId);
        ApiResponseMessege responseMessege = ApiResponseMessege.builder().messege("category is deleted successfully !!").status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(responseMessege,HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingle(@PathVariable String categoryId) {
        CategoryDto categoryDto = categoryService.get(categoryId);
        return ResponseEntity.ok(categoryDto);

    }

    //crete product with category
    @PostMapping("/{categoryId}/products")
    public ResponseEntity<ProductDto> creteProductWithCategory(@PathVariable String categoryId,@RequestBody ProductDto productDto ){

        ProductDto productWithCategory = productService.createWithCategory(productDto, categoryId);
        return new ResponseEntity<>(productWithCategory,HttpStatus.CREATED);
    }

    // update category of product
    @PutMapping("/{categoryId}/product/{productId}")
    public ResponseEntity<ProductDto> updateCategoryOfProduct(
            @PathVariable String categoryId,
            @PathVariable String productId
    ){
        ProductDto productDto = productService.updateCategory(productId, categoryId);
        return new ResponseEntity<>(productDto,HttpStatus.OK);
    }

}
