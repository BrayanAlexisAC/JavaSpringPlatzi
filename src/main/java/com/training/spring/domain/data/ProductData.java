package com.training.spring.domain.data;

import com.training.spring.persistence.entity.CategoryModel;
import jakarta.persistence.*;

public class ProductData {

    private Integer id;
    private String name;
    private Integer idCategory;
    private Double  price;
    private Integer stockQuantity;
    private Boolean available;
    private CategoryData category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public CategoryData getCategory() {
        return category;
    }

    public void setCategory(CategoryData category) {
        this.category = category;
    }
}
