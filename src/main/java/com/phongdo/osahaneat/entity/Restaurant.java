package com.phongdo.osahaneat.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "title")
    String title;

    @Column(name = "sub_title")
    String subTitle;

    @Column(name = "description")
    String desc;

    @Column(name = "image")
    String image;

    @Column(name = "is_freeship")
    boolean isFreeship;

    @Column(name = "address")
    String address;

    @Column(name = "open_time")
    Date openTime;

    @OneToMany(mappedBy = "restaurant")
    Set<Promo> listPromo;



    @OneToMany(mappedBy = "restaurant")
    Set<MenuRestaurant> listMenu;


    @OneToMany(mappedBy = "restaurant")
    Set<Orders> listOrders;


    @OneToMany(mappedBy = "restaurant")
    Set<RatingRestaurant> listRatingRestaurant;


}
