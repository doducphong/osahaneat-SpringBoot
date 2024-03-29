package com.phongdo.osahaneat.service.imp;

import com.phongdo.osahaneat.dto.CategoryDTO;
import com.phongdo.osahaneat.entity.Food;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CategoryServiceImp {
    List<CategoryDTO> getCategoryHomePage();
    boolean deleteCategory(@PathVariable int categoryId);
    boolean updateCategory(@PathVariable int categoryId, @RequestParam String nameCategory);


}
