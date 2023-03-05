package com.lcwd.electronicsStrore.electronicsStore.Controllers;

import com.lcwd.electronicsStrore.electronicsStore.DTO.ApiResponseMessege;
import com.lcwd.electronicsStrore.electronicsStore.DTO.ImageResponse;
import com.lcwd.electronicsStrore.electronicsStore.DTO.ProductDto;
import com.lcwd.electronicsStrore.electronicsStore.Services.FileSevice;
import com.lcwd.electronicsStrore.electronicsStore.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    private FileSevice fileSevice;
    @Value("${product.image.path}")
    private String imagepath;

    @PostMapping
    public ResponseEntity<ProductDto> createproduct(@RequestBody ProductDto productDto){
        ProductDto createdProduct = productService.create(productDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }


    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateproduct(@PathVariable String productId, @RequestBody ProductDto productDto) {
        ProductDto updatedProduct = productService.update(productDto, productId);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponseMessege> delete(@PathVariable String productId) {
        productService.delete(productId);
        ApiResponseMessege reponceMessege = ApiResponseMessege.builder().messege("product is deleted").status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(reponceMessege,HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String productId){
        ProductDto productDto = productService.get(productId);
        return new ResponseEntity<>(productDto,HttpStatus.OK);
    }

    //upload image
    @PostMapping("/image/{productId}")
    public ResponseEntity<ImageResponse> uploadProductImage(@PathVariable String productId,
                                                            @RequestParam("productImage")MultipartFile image) throws IOException {
        String fileName = fileSevice.uploadFile(image, imagepath);
        ProductDto productDto = productService.get(productId);
        productDto.setProductImageName(fileName);
        ProductDto updatedProduct = productService.update(productDto, productId);
        ImageResponse productImageIsSuccessfullyUploaded = ImageResponse.builder().imageName(updatedProduct.getProductImageName()).messege("product image is successfully uploaded").status(HttpStatus.CREATED).success(true).build();
        return new ResponseEntity<>(productImageIsSuccessfullyUploaded,HttpStatus.CREATED);
    }

    //serve image
    @GetMapping(value = "/image/{productId}")
    public void serveUserImage(@PathVariable String productId , HttpServletResponse response) throws IOException {
        ProductDto productDto=productService.get(productId);
        InputStream resource = fileSevice.getResource(imagepath, productDto.getProductImageName());
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
