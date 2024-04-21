package com.phongdo.osahaneat.mapper;

import com.phongdo.osahaneat.dto.CategoryDTO;
import com.phongdo.osahaneat.dto.RestaurantDTO;
import com.phongdo.osahaneat.entity.Category;
import com.phongdo.osahaneat.entity.MenuRestaurant;
import com.phongdo.osahaneat.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, FoodMapper.class})
public interface RestaurantMapper {

    @Mappings({
            @Mapping(source = "subTitle", target = "subtitle"),
            @Mapping(source = "openTime", target = "openDate"),
    })
    RestaurantDTO restaurantToRestaurantDTO(Restaurant restaurant);

}
