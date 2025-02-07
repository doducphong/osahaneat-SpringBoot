package com.phongdo.osahaneat.service.imp;

import com.phongdo.osahaneat.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.phongdo.osahaneat.domain.entity.Category;
import com.phongdo.osahaneat.domain.entity.Food;
import com.phongdo.osahaneat.repository.FoodRepository;

@Service
public class MenuServiceImpl implements com.phongdo.osahaneat.service.MenuService {
    @Autowired
    FileService fileService;

    @Autowired
    FoodRepository foodRepository;

    @Override
    public boolean createMenu(
            MultipartFile file, String title, boolean is_freeship, String time_ship, double price, int cate_id) {
        boolean isInsertSuccess = false;
        try {
            boolean isSaveFileSuccess = fileService.saveFile(file);
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
        } catch (Exception e) {
            System.out.println("Error insert restaurant" + e);
        }
        return isInsertSuccess;
    }
}
