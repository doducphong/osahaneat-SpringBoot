package com.phongdo.osahaneat.mapper;

import com.phongdo.osahaneat.dto.CategoryDTO;
import com.phongdo.osahaneat.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = FoodMapper.class)
public interface CategoryMapper {


    @Mappings({
            @Mapping(source = "nameCate", target = "name"),
            @Mapping(target = "menus", source = "listFood"),
    })
    CategoryDTO toDTO(Category category);

    List<CategoryDTO> toDTOList(List<Category> categories);

    @Mappings({
            @Mapping(source = "name", target = "nameCate"),
            @Mapping(target = "listFood", source = "menus"),
    })
    Category toEntity(CategoryDTO categoryDTO);
}
