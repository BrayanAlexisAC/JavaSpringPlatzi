package com.training.spring.domain.services;

import com.training.spring.domain.data.ProductData;

import java.util.List;

public interface ProductService {

    /**
     * Get all products
     * @return List<ProductData>
     */
    List<ProductData> getAll();

    /**
     * Get back a list of products by category
     * @param idCategory int
     * @param nativeMode boolean
     * @return List<ProductModel>
     */
    List<ProductData> getProductsByCategory(int idCategory, boolean nativeMode);

    /**
     * Get back a list of products by less stock quantity, only available products
     * @param stockQuantity int
     * @param nativeMode boolean
     * @return List<ProductModel>
     */
    List<ProductData> getProductsByStockAndAvailable(int stockQuantity, boolean nativeMode);

    /**
     * Get back a product by id
     * @param idProduct int
     * @return ProductModel
     */
    ProductData getProductById(int idProduct);

    /**
     * Save a product by id
     * @param productData ProductData
     * @return boolean
     */
    boolean save(ProductData productData);

    /**
     * Delete a product
     * @param productData ProductData
     * @return boolean
     */
    boolean delete(ProductData productData);

}
