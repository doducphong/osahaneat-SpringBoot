package com.phongdo.osahaneat.service.imp;

import com.phongdo.osahaneat.dto.CategoryDTO;
import com.phongdo.osahaneat.entity.Food;

import java.util.List;

public interface CategoryServiceImp {
    List<CategoryDTO> getCategoryHomePage();
}
