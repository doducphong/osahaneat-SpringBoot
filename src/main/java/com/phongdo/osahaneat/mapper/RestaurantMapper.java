package com.phongdo.osahaneat.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.phongdo.osahaneat.dto.response.RestaurantDTO;
import com.phongdo.osahaneat.domain.entity.Restaurant;

@Mapper(
        componentModel = "spring",
        uses = {CategoryMapper.class, FoodMapper.class})
public interface RestaurantMapper {

    @Mappings({
        @Mapping(source = "subTitle", target = "subtitle"),
        @Mapping(source = "openTime", target = "openDate"),
    })
    RestaurantDTO restaurantToRestaurantDTO(Restaurant restaurant);
}
