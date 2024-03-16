package com.phongdo.osahaneat.service;

import com.phongdo.osahaneat.dto.RestaurantDTO;
import com.phongdo.osahaneat.entity.RatingRestaurant;
import com.phongdo.osahaneat.entity.Restaurant;
import com.phongdo.osahaneat.repository.RestaurantRepository;
import com.phongdo.osahaneat.service.imp.FileServiceImp;
import com.phongdo.osahaneat.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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

    @Override
    public List<RestaurantDTO> getHomepageRestaurant() {
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0,6);
        Page<Restaurant> listData = restaurantRepository.findAll(pageRequest);
        for (Restaurant data : listData) {
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setImage(data.getImage());
            restaurantDTO.setTitle(data.getTitle());
            restaurantDTO.setFreeShip(data.isFreeship());
            restaurantDTO.setSubtitle(data.getSubTitle());
            restaurantDTO.setRating(calculatorRating(data.getListRatingRestaurant()));

            restaurantDTOS.add(restaurantDTO);
        }
        return restaurantDTOS;
    }

    public double calculatorRating(Set<RatingRestaurant> listRating){
        double totalPoin = 0;
        for (RatingRestaurant data : listRating) {
            totalPoin += data.getRatePoint();
        }
        return totalPoin/listRating.size();
    }
}
