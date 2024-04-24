package com.phongdo.osahaneat.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity(name = "promo")
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "res_id")
    Restaurant restaurant;

    @Column(name = "percent")
    int percent;

    @Column(name = "start_date")
    Date startDate;

    @Column(name = "end_date")
    Date endDate;


}
