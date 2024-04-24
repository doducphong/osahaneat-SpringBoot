package com.phongdo.osahaneat.mapper;

import com.phongdo.osahaneat.dto.response.RestaurantDTO;
import com.phongdo.osahaneat.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, FoodMapper.class})
public interface RestaurantMapper {

    @Mappings({
            @Mapping(source = "subTitle", target = "subtitle"),
            @Mapping(source = "openTime", target = "openDate"),
    })
    RestaurantDTO restaurantToRestaurantDTO(Restaurant restaurant);

}
