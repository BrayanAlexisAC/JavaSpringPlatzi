package com.training.spring.persistence.entity;

import com.training.spring.persistence.entity.composedPK.OrderByProductsPK;
import jakarta.persistence.*;

@Entity
@Table(name = "COMPRAS_PRODUCTOS")
public class OrderByProductsModel {

    @EmbeddedId
    private OrderByProductsPK composedId;

    @Column(name = "cantidad")
    private Integer quantity;

    @Column(name = "total")
    private Double totalPrice;

    @Column(name = "estado")
    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private OrderModel order;

    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private ProductModel product;

    public OrderByProductsPK getComposedId() {
        return composedId;
    }

    public void setComposedId(OrderByProductsPK composedId) {
        this.composedId = composedId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }
}
