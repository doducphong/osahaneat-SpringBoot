package com.phongdo.osahaneat.entity;

import com.phongdo.osahaneat.entity.keys.KeyOrderItem;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "orderItem")
public class OrderItem {
    @EmbeddedId
    KeyOrderItem keys;

    @ManyToOne
    @JoinColumn(name = "order_id",insertable = false,updatable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "food_id",updatable = false,insertable = false)
    private Food food;

    @Column(name = "create_date")
    private Date careateDate;

    public KeyOrderItem getKeys() {
        return keys;
    }

    public void setKeys(KeyOrderItem keys) {
        this.keys = keys;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Date getCareateDate() {
        return careateDate;
    }

    public void setCareateDate(Date careateDate) {
        this.careateDate = careateDate;
    }
}
