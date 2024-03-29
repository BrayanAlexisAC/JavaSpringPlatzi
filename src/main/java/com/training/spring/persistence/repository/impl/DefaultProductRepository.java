package com.training.spring.persistence.repository.impl;

import com.training.spring.domain.data.ProductData;
import com.training.spring.persistence.crud.ProductCrudRepository;
import com.training.spring.persistence.entity.ProductModel;
import com.training.spring.persistence.mappers.ProductMapper;
import com.training.spring.persistence.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class DefaultProductRepository implements ProductRepository {
    Logger log = LoggerFactory.getLogger(DefaultProductRepository.class);

    @Autowired
    protected ProductCrudRepository repository;

    @Autowired
    protected ProductMapper mapper;

    @Override
    public List<ProductData> getAll(){
        List<ProductModel> lstProducts = (List<ProductModel>) repository.findAll();
        return mapper.toProducts(lstProducts);
    }

    @Override
    public List<ProductData> getProductsByCategory(int idCategory, boolean nativeMode){
        if (nativeMode){
            List<ProductModel> productModel = repository.getProductsByCategory(idCategory);
            return mapper.toProducts(productModel);
        } else {
            Optional<List<ProductModel>> lstProducts = repository.findByIdCategoryOrderByIdAsc(idCategory);
            return mapper.toProducts(lstProducts.orElse(Collections.emptyList()));
        }
    }

    @Override
    public List<ProductData> getProductsByStockAndAvailable(int stockQuantity, boolean nativeMode){
        if (nativeMode){
            List<ProductModel> lstProducts = repository.getProductsByStockAndAvailable(stockQuantity, true);
            return mapper.toProducts(lstProducts);
        } else {
            Optional<List<ProductModel>> listProducts = repository.findByStockQuantityLessThanAndAvailable(stockQuantity, true);
            return mapper.toProducts(listProducts.orElse(Collections.emptyList()));
        }
    }

    @Override
    public ProductData getProductById(int idProduct){
        Optional<ProductModel> productModel = repository.findById(idProduct);
        return productModel.map(product -> mapper.toProductData(product)).orElse(null);
    }

    @Override
    public ProductData save(ProductData productData){
        try {
            ProductModel productModel = mapper.toProductModel(productData);
            productModel = repository.save(productModel);
            return mapper.toProductData(productModel);
        } catch (Exception e){
            log.error("Error with save product message:{}, stacktrace:{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    @Override
    public boolean delete(ProductData productData){
        try {
            ProductModel productModel = mapper.toProductModel(productData);
            repository.delete(productModel);
            return true;
        } catch (Exception e){
            log.error("Error with delete product message:{}, stacktrace:{}", e.getMessage(), Arrays.toString(e.getStackTrace()));
            return false;
        }
    }
}
