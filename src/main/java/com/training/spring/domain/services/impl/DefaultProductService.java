package com.training.spring.domain.services.impl;

import com.training.spring.domain.data.ProductData;
import com.training.spring.domain.services.ProductService;

import java.util.List;

public class DefaultProductService implements ProductService {

    @Override
    public List<ProductData> getAll() {
        return null;
    }

    @Override
    public List<ProductData> getProductsByCategory(int idCategory, boolean nativeMode) {
        return null;
    }

    @Override
    public List<ProductData> getProductsByStockAndAvailable(int stockQuantity, boolean nativeMode) {
        return null;
    }

    @Override
    public ProductData getProductById(int idProduct) {
        return null;
    }

    @Override
    public boolean save(ProductData productData) {
        return false;
    }

    @Override
    public boolean delete(ProductData productData) {
        return false;
    }
}
