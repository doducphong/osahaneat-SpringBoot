package com.phongdo.osahaneat.domain.entity;

import java.util.Date;

import jakarta.persistence.*;

import com.phongdo.osahaneat.domain.entity.keys.KeyOrderItem;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "orderItem")
public class OrderItem {
    @EmbeddedId
    KeyOrderItem keys;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    Orders orders;

    @ManyToOne
    @JoinColumn(name = "food_id", updatable = false, insertable = false)
    Food food;

    @Column(name = "create_date")
    Date careateDate;
}
