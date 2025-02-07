package com.phongdo.osahaneat.domain.entity;

import java.util.Date;

import jakarta.persistence.*;

import com.phongdo.osahaneat.domain.entity.keys.KeyMenu;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "menu")
public class MenuRestaurant {

    @EmbeddedId
    KeyMenu keys;

    @ManyToOne
    @JoinColumn(name = "cate_id", updatable = false, insertable = false)
    Category category;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", insertable = false, updatable = false)
    Restaurant restaurant;

    @Column(name = "create_date")
    Date createDate;
}
