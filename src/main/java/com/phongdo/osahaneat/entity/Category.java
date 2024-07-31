package com.phongdo.osahaneat.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name_cate")
    String nameCate;

    @Column(name = "create_date")
    Date createDate;

    @OneToMany(mappedBy = "category")
    Set<MenuRestaurant> listMenu;

    @OneToMany(mappedBy = "category")
    Set<Food> listFood;
}
