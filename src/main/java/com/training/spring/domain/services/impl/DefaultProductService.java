package com.training.spring.domain.services.impl;

import com.training.spring.Constants;
import com.training.spring.domain.data.ProductData;
import com.training.spring.domain.services.ProductService;
import com.training.spring.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.List;

public class DefaultProductService implements ProductService {

    @Autowired
    ProductRepository repository;

    @Autowired
    Environment environment;

    @Override
    public List<ProductData> getAll() {
        return repository.getAll();
    }

    @Override
    public List<ProductData> getProductsByCategory(int idCategory) {
        boolean isNativeMode = Boolean.TRUE.equals(environment.getProperty(Constants.PRODUCTS_CRUD_REPOSITORY_NATIVE, Boolean.class));
        return repository.getProductsByCategory(idCategory, isNativeMode);
    }

    @Override
    public List<ProductData> getProductsByStockAndAvailable(int stockQuantity) {
        boolean isNativeMode = Boolean.TRUE.equals(environment.getProperty(Constants.PRODUCTS_CRUD_REPOSITORY_NATIVE, Boolean.class));
        return repository.getProductsByStockAndAvailable(stockQuantity,isNativeMode);
    }

    @Override
    public ProductData getProductById(int idProduct) {
        return repository.getProductById(idProduct);
    }

    @Override
    public boolean save(ProductData productData) {
        return repository.save(productData);
    }

    @Override
    public boolean delete(ProductData productData) {
        return repository.delete(productData);
    }
}
