package com.phongdo.osahaneat.service.imp;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.phongdo.osahaneat.dto.response.CategoryDTO;

public interface CategoryServiceImp {
    List<CategoryDTO> getCategoryHomePage();

    boolean deleteCategory(@PathVariable int categoryId);

    boolean updateCategory(@PathVariable int categoryId, @RequestParam String nameCategory);

    CategoryDTO addCategory(@RequestBody CategoryDTO categoryDTO);
}
