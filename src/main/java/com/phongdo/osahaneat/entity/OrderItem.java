package com.phongdo.osahaneat.entity;

import com.phongdo.osahaneat.entity.keys.KeyOrderItem;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity(name = "orderItem")
public class OrderItem {
    @EmbeddedId
    KeyOrderItem keys;

    @ManyToOne
    @JoinColumn(name = "order_id",insertable = false,updatable = false)
    Orders orders;

    @ManyToOne
    @JoinColumn(name = "food_id",updatable = false,insertable = false)
    Food food;

    @Column(name = "create_date")
    Date careateDate;


}
