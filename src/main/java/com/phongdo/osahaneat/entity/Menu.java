package com.phongdo.osahaneat.entity;

import com.phongdo.osahaneat.entity.keys.KeyMenu;
import jakarta.persistence.*;

import java.util.Date;


@Entity(name = "menu")
public class Menu {

    @EmbeddedId
    KeyMenu keys;

    @ManyToOne
    @JoinColumn(name = "cate_id",updatable = false,insertable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", insertable = false,updatable = false)
    private Restaurant restaurant;

    @Column(name = "create_date")
    private Date createDate;

    public KeyMenu getKeys() {
        return keys;
    }

    public void setKeys(KeyMenu keys) {
        this.keys = keys;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
