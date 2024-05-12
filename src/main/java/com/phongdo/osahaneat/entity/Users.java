package com.phongdo.osahaneat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Entity(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "user_name")
    String userName;
    @Column(name = "password")
    String password;
    @Column(name = "full_name")
    String fullname;
    @Column(name = "create_date")
    Date createDate;

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    Set<Orders> listOrders;


    @OneToMany(mappedBy = "users")
    @JsonIgnore
    Set<RatingRestaurant> listRatingRestaurant;

    @ManyToMany
    Set<Roles> roles;

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    Set<RatingFood> listRatingFood;


}
