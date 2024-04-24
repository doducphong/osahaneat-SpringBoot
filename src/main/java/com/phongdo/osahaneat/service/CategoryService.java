package com.phongdo.osahaneat.service;

import com.phongdo.osahaneat.dto.response.CategoryDTO;
import com.phongdo.osahaneat.entity.Category;
import com.phongdo.osahaneat.mapper.CategoryMapper;
import com.phongdo.osahaneat.repository.CategoryRepository;
import com.phongdo.osahaneat.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryMapper categoryMapper;


    @Override
    public List<CategoryDTO> getCategoryHomePage() {
        PageRequest pageRequest = PageRequest.of(0,3, Sort.by("id"));
        Page<Category> listCategory = categoryRepository.findAll(pageRequest);


        return listCategory.getContent().stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
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
        Category category = categoryMapper.toEntity(categoryDTO);
        category.setCreateDate(new Date());
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDTO(savedCategory);
    }

}
