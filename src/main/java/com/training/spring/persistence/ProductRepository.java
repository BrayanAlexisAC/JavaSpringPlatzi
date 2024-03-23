package com.training.spring.persistence;

import com.training.spring.persistence.crud.ProductCrudRepository;
import com.training.spring.persistence.entity.ProductModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    Logger log = LoggerFactory.getLogger(ProductRepository.class);
    private ProductCrudRepository repository;

    /**
     * Get all products
     * @return List<ProductModel>
     */
    public List<ProductModel> getAll(){
        return (List<ProductModel>) repository.findAll();
    }

    /**
     * Get back a list of products by category
     * @param idCategory int
     * @param nativeMode boolean
     * @return List<ProductModel>
     */
    public List<ProductModel> getProductsByCategory(int idCategory, boolean nativeMode){
        if (nativeMode){
            return repository.getProductsByCategory(idCategory);
        } else {
            Optional<List<ProductModel>> listProducts = repository.findByIdCategoryOrderByIdAsc(idCategory);
            return listProducts.orElse(Collections.emptyList());
        }
    }

    /**
     * Get back a list of products by less stock quantity, only available products
     * @param stockQuantity int
     * @param nativeMode boolean
     * @return List<ProductModel>
     */
    public List<ProductModel> getProductsByStockAndAvailable(int stockQuantity, boolean nativeMode){
        if (nativeMode){
            return repository.getProductsByStockAndAvailable(stockQuantity, true);
        } else {
            Optional<List<ProductModel>> listProducts = repository.findByStockQuantityLessThanAndAvailable(stockQuantity, true);
            return listProducts.orElse(Collections.emptyList());
        }
    }

    /**
     * Get back a product by id
     * @param idProduct int
     * @return ProductModel
     */
    public ProductModel getProductById(int idProduct){
        Optional<ProductModel> productModel = repository.findById(idProduct);
        return productModel.orElse(null);
    }

    /**
     * Save a product by id
     * @param productModel ProductModel
     * @return boolean
     */
    public boolean save(ProductModel productModel){
        try {
            repository.save(productModel);
            return true;
        } catch (Exception e){
            log.error("Error with save product message:{}, stacktrace:{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
            return false;
        }
    }

    // Delete a product by model
    public boolean delete(ProductModel productModel){
        try {
            repository.delete(productModel);
            return true;
        } catch (Exception e){
            log.error("Error with delete product message:{}, stacktrace:{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
            return false;
        }
    }
}
