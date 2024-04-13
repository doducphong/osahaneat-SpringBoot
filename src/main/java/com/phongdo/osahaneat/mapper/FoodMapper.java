package com.phongdo.osahaneat.mapper;

import com.phongdo.osahaneat.dto.MenuDTO;
import com.phongdo.osahaneat.entity.Food;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoodMapper {



    Food toEntity(MenuDTO menuDTO);

    MenuDTO toDTO(Food food);

    List<MenuDTO> toDTOList(List<Food> foods);
}
