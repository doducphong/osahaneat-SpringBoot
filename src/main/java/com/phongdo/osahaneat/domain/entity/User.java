package com.phongdo.osahaneat.domain.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "user_name", unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String userName;

    @Column(name = "password")
    String password;

    @Column(name = "full_name")
    String fullname;

    @Column(name = "create_date")
    Date createDate;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    Set<Orders> listOrders;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    Set<RatingRestaurant> listRatingRestaurant;

    @ManyToMany
    Set<Role> roles;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    Set<RatingFood> listRatingFood;
}
