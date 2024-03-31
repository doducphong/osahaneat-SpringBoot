package com.phongdo.osahaneat.service;

import com.phongdo.osahaneat.dto.CategoryDTO;
import com.phongdo.osahaneat.dto.MenuDTO;
import com.phongdo.osahaneat.entity.Category;
import com.phongdo.osahaneat.entity.Food;
import com.phongdo.osahaneat.repository.CategoryRepository;
import com.phongdo.osahaneat.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getCategoryHomePage() {
        PageRequest pageRequest = PageRequest.of(0,3, Sort.by("id"));
        Page<Category> listCategory = categoryRepository.findAll(pageRequest);
        List<CategoryDTO> listCategoryDTOS = new ArrayList<>();
        for (Category data : listCategory) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(data.getNameCate());

            List<MenuDTO> menuDTOS = new ArrayList<>();
            for (Food dataFood : data.getListFood()) {
                MenuDTO menuDTO = new MenuDTO();
                menuDTO.setImage(dataFood.getImage());
                menuDTO.setTitle(dataFood.getTitle());
                menuDTO.setFreeShip(dataFood.isFreeShip());

                menuDTOS.add(menuDTO);
            }
            categoryDTO.setMenus(menuDTOS);
            listCategoryDTOS.add(categoryDTO);
        }

        return listCategoryDTOS;
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        try{
            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
            if(categoryOptional.isPresent()){
                categoryRepository.deleteById(categoryId);
                return true;
            }
            else {
                return false;
            }
        }catch (Exception e){
            System.out.println("Error delete category " + e);
            return false;
        }
    }

    @Override
    public boolean updateCategory(int categoryId, String nameCategory) {
        try{
            Optional<Category> category = categoryRepository.findById(categoryId);
            if (category.isPresent()){
                Category existingCategory = category.get();
                existingCategory.setNameCate(nameCategory);
                categoryRepository.save(existingCategory);
                return true;
            }else {
                return false;
            }

        }catch (Exception e){
            System.out.println("Error update category " + e);
            return false;
        }

    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setNameCate(categoryDTO.getName());

        Category savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }

    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getNameCate());
        return categoryDTO;
    }
}
