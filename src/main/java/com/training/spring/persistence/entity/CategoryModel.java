package com.training.spring.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CATEGORIAS")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer id;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "estado")
    private Boolean available;

    @OneToMany(mappedBy = "category")
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
