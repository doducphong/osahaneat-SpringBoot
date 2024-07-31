package com.phongdo.osahaneat.entity;

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
@Entity(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "title")
    String title;

    @Column(name = "image")
    String image;

    @Column(name = "time_ship")
    String timeShip;

    @Column(name = "price")
    double price;

    @Column(name = "is_freeship")
    boolean isFreeShip;

    @Column(name = "description")
    String desc;

    @OneToMany(mappedBy = "food")
    Set<OrderItem> listOrderItem;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @OneToMany(mappedBy = "food")
    Set<RatingFood> listRatingFood;
}
