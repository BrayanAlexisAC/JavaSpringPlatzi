package com.training.spring.persistence.entity;

import jakarta.persistence.*;

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
}
