package com.phongdo.osahaneat.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "full_name")
    private String fullname;
    @Column(name = "create_date")
    private Date createDate;

    @OneToMany(mappedBy = "users")
    private Set<Orders> listOrders;

    public Set<Orders> getListOrder() {
        return listOrders;
    }

    public void setListOrder(Set<Orders> listOrders) {
        this.listOrders = listOrders;
    }

    @OneToMany(mappedBy = "users")
    private Set<RatingRestaurant> listRatingRestaurant;

    public Set<RatingRestaurant> getListRatingRestaurant() {
        return listRatingRestaurant;
    }

    public void setListRatingRestaurant(Set<RatingRestaurant> listRatingRestaurant) {
        this.listRatingRestaurant = listRatingRestaurant;
    }

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles roles;

    @OneToMany(mappedBy = "users")
    private Set<RatingFood> listRatingFood;

    public Set<RatingFood> getListRatingFood() {
        return listRatingFood;
    }

    public void setListRatingFood(Set<RatingFood> listRatingFood) {
        this.listRatingFood = listRatingFood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}
