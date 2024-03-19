package com.training.spring.persistence.entity.composedPK;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class OrderByProductsPK {

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
