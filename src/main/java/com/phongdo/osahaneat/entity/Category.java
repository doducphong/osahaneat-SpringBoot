package com.phongdo.osahaneat.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_cate")
    private String nameCate;
    @Column(name = "create_date")
    private Date createDate;

    @OneToMany(mappedBy = "category")
    private Set<MenuRestaurant> listMenu;

    public Set<MenuRestaurant> getListMenu() {
        return listMenu;
    }

    public void setListMenu(Set<MenuRestaurant> listMenu) {
        this.listMenu = listMenu;
    }

    @OneToMany(mappedBy = "category")
    private Set<Food> listFood;

    public Set<Food> getListFood() {
        return listFood;
    }

    public void setListFood(Set<Food> listFood) {
        this.listFood = listFood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
