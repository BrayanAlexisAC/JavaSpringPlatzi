package com.training.spring.domain.data;

import java.time.LocalDateTime;
import java.util.List;

public class PucharseData {
    private int pucharseId;
    private String clientId;
    private LocalDateTime createDate;
    private Character paymentMethod;
    private String comment;
    private Character status;
    private List<PucharseItemData> items;

    public int getPucharseId() {
        return pucharseId;
    }

    public void setPucharseId(int pucharseId) {
        this.pucharseId = pucharseId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Character getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Character paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public List<PucharseItemData> getItems() {
        return items;
    }

    public void setItems(List<PucharseItemData> items) {
        this.items = items;
    }
}
