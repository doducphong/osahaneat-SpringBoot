package com.phongdo.osahaneat.entity;

import com.phongdo.osahaneat.entity.keys.KeyMenu;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity(name = "menu")
public class MenuRestaurant {

    @EmbeddedId
    KeyMenu keys;

    @ManyToOne
    @JoinColumn(name = "cate_id",updatable = false,insertable = false)
    Category category;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", insertable = false,updatable = false)
    Restaurant restaurant;

    @Column(name = "create_date")
    Date createDate;


}
