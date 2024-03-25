package com.training.spring.web.controllers;

import com.training.spring.domain.data.ProductData;
import com.training.spring.domain.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/products")
public class ProductController {
    Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<ProductData> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{productId}")
    public ProductData getProduct(@PathVariable("productId") int productId){
        return productService.getProductById(productId);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductData> getProductsByCategory(@PathVariable("categoryId") int categoryId){
        return productService.getProductsByCategory(categoryId);
    }

    @GetMapping("/stock/{maxStock}")
    public List<ProductData> getProductsLessStock(@PathVariable("maxStock") int maxStock){
        return productService.getProductsByStockAndAvailable(maxStock);
    }

    @PostMapping("/save")
    public ProductData save(@RequestBody ProductData productData){
        return productService.save(productData);
    }

    @DeleteMapping("/delete/{productId}")
    public boolean delete(@PathVariable("productId") int productId) throws Exception {
        ProductData productData = productService.getProductById(productId);
        if (Objects.nonNull(productData)){
            return productService.delete(productData);
        } else {
            throw new Exception("Product does not exist");
        }
    }

}
