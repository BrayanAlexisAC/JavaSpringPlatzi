package com.training.spring.web.controllers;

import com.training.spring.domain.data.ProductData;
import com.training.spring.domain.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Products Controller", description = "Get back only information about products")
public class ProductController {
    Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @Operation(
            method = "GET",
            operationId = "all",
            summary = "Get back all Products in the database",
            description = "Get back all products without exceptions")
    @ApiResponses({
            @ApiResponse(responseCode =  "200", description = "Get back a product list"),
            @ApiResponse(responseCode =  "401", description = "Wrong credentials, Forbidden", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode =  "404", description = "Get back an empty list", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<List<ProductData> > getAll(){
        List<ProductData> lstProducts = productService.getAll();
        if (lstProducts.isEmpty()){
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lstProducts, HttpStatus.OK);
        }
    }

    @GetMapping("/{productId}")
    @Operation(
            method = "GET",
            operationId = "productId",
            summary = "Get a product",
            description = "Get all information about a product")
    @ApiResponses({
            @ApiResponse(responseCode =  "200", description = "Get a product information"),
            @ApiResponse(responseCode =  "401", description = "Wrong credentials, Forbidden", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode =  "404", description = "Get back an error code", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<ProductData> getProduct(@Parameter(description = "Product identifier", example = "10", required = true) @PathVariable("productId") int productId){
        ProductData productData = productService.getProductById(productId);
        return Objects.nonNull(productData) ? new ResponseEntity<>(productData, HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/category/{categoryId}")
    @Operation(
            method = "GET",
            operationId = "category/categoryId",
            summary = "Get products in a category",
            description = "Get everything products in an specific category")
    @ApiResponses({
            @ApiResponse(responseCode =  "200", description = "Get a product list"),
            @ApiResponse(responseCode =  "401", description = "Wrong credentials, Forbidden", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode =  "404", description = "Returns an error code", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<List<ProductData>> getProductsByCategory(@Parameter(description = "Category identifier", example = "5", required = true) @PathVariable("categoryId") int categoryId){
        List<ProductData> lstProducts = productService.getProductsByCategory(categoryId);
        if (lstProducts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lstProducts, HttpStatus.OK);
        }
    }

    @GetMapping("/stock/{maxStock}")
    @Operation(
            method = "GET",
            operationId = "stock/maxStock",
            summary = "Get products less specified stock",
            description = "Get products with stock less than or equal to the specified maximum stock")
    @ApiResponses({
            @ApiResponse(responseCode =  "200", description = "Get a product list"),
            @ApiResponse(responseCode =  "401", description = "Wrong credentials, Forbidden", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode =  "404", description = "Returns an error code", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<List<ProductData>> getProductsLessStock(@PathVariable("maxStock") int maxStock){
        List<ProductData> lstProducts = productService.getProductsByStockAndAvailable(maxStock);
        if (lstProducts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lstProducts, HttpStatus.OK);
        }
    }

    @PostMapping("/save")
    @Operation(
            method = "POST",
            operationId = "save",
            summary = "Save a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product saved successfully"),
            @ApiResponse(responseCode = "401", description = "Wrong credentials, Forbidden", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "406", description = "Product could not be saved due to invalid data", content = @Content(schema = @Schema())),
    })
    public ResponseEntity<ProductData> save(@Parameter(description = "Product data to be saved", required = true) @RequestBody ProductData productData){
        ProductData saveProduct = productService.save(productData);
        return Objects.nonNull(saveProduct) && saveProduct.getId() > 0 ? new ResponseEntity<>(saveProduct, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("/delete/{productId}")
    @Operation(
            summary = "Delete a product by ID",
            method = "DELETE",
            operationId = "deleteProductById"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "406", description = "Product could not be deleted", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<Object> delete(@Parameter(description = "ID of the product to delete", example = "10", required = true) @PathVariable("productId") int productId) throws Exception {
        ProductData productData = productService.getProductById(productId);
        return productService.delete(productData) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}
