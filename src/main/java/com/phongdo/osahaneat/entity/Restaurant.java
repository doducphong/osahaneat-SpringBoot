package com.phongdo.osahaneat.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "sub_title")
    private String subTitle;

    @Column(name = "description")
    private String desc;

    @Column(name = "image")
    private String image;

    @Column(name = "is_freeship")
    private boolean isFreeship;

    @Column(name = "address")
    private String address;

    @Column(name = "open_time")
    private Date openTime;

    @OneToMany(mappedBy = "restaurant")
    private Set<Promo> listPromo;

    public Set<Promo> getListPromo() {
        return listPromo;
    }

    public void setListPromo(Set<Promo> listPromo) {
        this.listPromo = listPromo;
    }

    @OneToMany(mappedBy = "restaurant")
    private Set<Menu> listMenu;

    public Set<Menu> getListMenu() {
        return listMenu;
    }

    public void setListMenu(Set<Menu> listMenu) {
        this.listMenu = listMenu;
    }

    @OneToMany(mappedBy = "restaurant")
    private Set<Orders> listOrders;

    public Set<Orders> getListOrders() {
        return listOrders;
    }

    public void setListOrders(Set<Orders> listOrders) {
        this.listOrders = listOrders;
    }

    @OneToMany(mappedBy = "restaurant")
    private Set<RatingRestaurant> listRatingRestaurant;

    public Set<RatingRestaurant> getListRatingRestaurant() {
        return listRatingRestaurant;
    }

    public void setListRatingRestaurant(Set<RatingRestaurant> listRatingRestaurant) {
        this.listRatingRestaurant = listRatingRestaurant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFreeship() {
        return isFreeship;
    }

    public void setFreeship(boolean freeship) {
        isFreeship = freeship;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }
}
