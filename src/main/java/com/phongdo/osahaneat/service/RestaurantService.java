package com.phongdo.osahaneat.service;

import com.phongdo.osahaneat.entity.Restaurant;
import com.phongdo.osahaneat.repository.RestaurantRepository;
import com.phongdo.osahaneat.service.imp.FileServiceImp;
import com.phongdo.osahaneat.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@Service
public class RestaurantService implements RestaurantServiceImp {

    @Autowired
    FileServiceImp fileServiceImp;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public boolean insertRestaurant(MultipartFile file, String title, String sub_title, String description, boolean is_freeship, String address, String open_time) {
        boolean isInsertSuccess = false;
        try {
            boolean isSaveFileSuccess = fileServiceImp.saveFile(file);
            if (isSaveFileSuccess) {
                Restaurant restaurant = new Restaurant();
                restaurant.setTitle(title);
                restaurant.setSubTitle(sub_title);
                restaurant.setDesc(description);
                restaurant.setImage(file.getOriginalFilename());
                restaurant.setFreeship(is_freeship);
                restaurant.setAddress(address);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                Date openDate = simpleDateFormat.parse(open_time);

                restaurant.setOpenTime(openDate);
                restaurantRepository.save(restaurant);
                isInsertSuccess = true;
            }
        }catch(Exception e){
            System.out.println("Error insert restaurant" + e);
        }
        return isInsertSuccess;
    }
}
