package com.phongdo.osahaneat.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuDTO {
    int id;
    String title;
    String image;
    boolean isFreeShip;
    String desc;
    double price;
    String timeShip;
}
