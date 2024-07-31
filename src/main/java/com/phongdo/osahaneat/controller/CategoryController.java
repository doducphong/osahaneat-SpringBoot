package com.phongdo.osahaneat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.phongdo.osahaneat.dto.response.CategoryDTO;
import com.phongdo.osahaneat.dto.response.ResponseData;
import com.phongdo.osahaneat.service.imp.CategoryServiceImp;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryServiceImp categoryServiceImp;

    @GetMapping()
    public ResponseEntity<?> getHomeCategory() {
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryServiceImp.getCategoryHomePage());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable int categoryId) {
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryServiceImp.deleteCategory(categoryId));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(
            @PathVariable("categoryId") int categoryId, @RequestParam String nameCategory) {
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryServiceImp.updateCategory(categoryId, nameCategory));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO) {
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryServiceImp.addCategory(categoryDTO));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
