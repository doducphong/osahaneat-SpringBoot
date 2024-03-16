package com.phongdo.osahaneat.service;

import com.phongdo.osahaneat.entity.Category;
import com.phongdo.osahaneat.entity.Food;
import com.phongdo.osahaneat.entity.Restaurant;
import com.phongdo.osahaneat.repository.FoodRepository;
import com.phongdo.osahaneat.service.imp.FileServiceImp;
import com.phongdo.osahaneat.service.imp.MenuServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MenuService implements MenuServiceImp {
    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    FoodRepository foodRepository;

    @Override
    public boolean createMenu(MultipartFile file,String title, boolean is_freeship, String time_ship, double price, int cate_id) {
        boolean isInsertSuccess = false;
        try {
            boolean isSaveFileSuccess = fileServiceImp.saveFile(file);
            if (isSaveFileSuccess) {
                Food food = new Food();
                food.setFreeShip(is_freeship);
                food.setImage(file.getOriginalFilename());
                food.setTitle(title);
                food.setPrice(price);


                Category category = new Category();
                category.setId(cate_id);

                food.setCategory(category);
                foodRepository.save(food);
                isInsertSuccess = true;
            }
        }catch(Exception e){
            System.out.println("Error insert restaurant" + e);
        }
        return isInsertSuccess;
    }
}
