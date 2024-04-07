package com.phongdo.osahaneat.service;

import com.phongdo.osahaneat.dto.CategoryDTO;
import com.phongdo.osahaneat.dto.MenuDTO;
import com.phongdo.osahaneat.entity.Category;
import com.phongdo.osahaneat.entity.Food;
import com.phongdo.osahaneat.repository.CategoryRepository;
import com.phongdo.osahaneat.service.imp.CategoryServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.print.attribute.standard.Destination;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    CategoryRepository categoryRepository;



    @Override
    public List<CategoryDTO> getCategoryHomePage() {
        PageRequest pageRequest = PageRequest.of(0,3, Sort.by("id"));
        Page<Category> listCategory = categoryRepository.findAll(pageRequest);
        List<CategoryDTO> listCategoryDTOS = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Category data : listCategory) {
            CategoryDTO categoryDTO = modelMapper.map(data, CategoryDTO.class);
            categoryDTO.setMenus(data.getListFood().stream()
                    .map(food -> modelMapper.map(food, MenuDTO.class))
                    .collect(Collectors.toList()));
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
        ModelMapper modelMapper = new ModelMapper();
        Category category = new Category();
        category.setNameCate(categoryDTO.getName());
        category.setCreateDate(new Date());
        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

}
