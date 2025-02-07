package com.phongdo.osahaneat.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.phongdo.osahaneat.dto.response.CategoryDTO;
import com.phongdo.osahaneat.domain.entity.Category;

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
