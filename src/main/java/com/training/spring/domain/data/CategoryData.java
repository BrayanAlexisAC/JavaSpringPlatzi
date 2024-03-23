package com.training.spring.domain.data;

import com.training.spring.persistence.entity.ProductModel;
import jakarta.persistence.*;

import java.util.List;

public class CategoryData {

    private Integer id;

    private String description;

    private Boolean available;

    private List<ProductModel> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
}
