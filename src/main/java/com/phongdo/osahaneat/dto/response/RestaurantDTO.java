package com.phongdo.osahaneat.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantDTO {
    int id;
    String image;
    String title;
    double rating;
    String subtitle;
    boolean isFreeShip;
    Date openDate;
    String desc;
    List<CategoryDTO> categoryDTOList;


}
