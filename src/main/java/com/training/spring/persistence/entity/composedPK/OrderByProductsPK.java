package com.training.spring.persistence.entity.composedPK;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderByProductsPK implements Serializable {

    @Column(name = "id_compra")
    private Integer idOrder;

    @Column(name = "id_producto")
    private Integer idProduct;

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }
}
