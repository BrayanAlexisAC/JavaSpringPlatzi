package com.training.spring.web.controllers;

import com.training.spring.domain.data.ProductData;
import com.training.spring.domain.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/products")
public class ProductController {
    Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductData> > getAll(){
        List<ProductData> lstProducts = productService.getAll();
        if (lstProducts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lstProducts, HttpStatus.OK);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductData> getProduct(@PathVariable("productId") int productId){
        ProductData productData = productService.getProductById(productId);
        return Objects.nonNull(productData) ? new ResponseEntity<>(productData, HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductData>> getProductsByCategory(@PathVariable("categoryId") int categoryId){
        List<ProductData> lstProducts = productService.getProductsByCategory(categoryId);
        if (lstProducts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lstProducts, HttpStatus.OK);
        }
    }

    @GetMapping("/stock/{maxStock}")
    public ResponseEntity<List<ProductData>> getProductsLessStock(@PathVariable("maxStock") int maxStock){
        List<ProductData> lstProducts = productService.getProductsByStockAndAvailable(maxStock);
        if (lstProducts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lstProducts, HttpStatus.OK);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ProductData> save(@RequestBody ProductData productData){
        // will fix, product does not get back idProduct
        ProductData saveProduct = productService.save(productData);
        return Objects.nonNull(saveProduct) && saveProduct.getId() > 0 ? new ResponseEntity<>(productData, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Object> delete(@PathVariable("productId") int productId) throws Exception {
        ProductData productData = productService.getProductById(productId);
        return productService.delete(productData) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
