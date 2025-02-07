package com.phongdo.osahaneat.service;

import com.phongdo.osahaneat.dto.response.RestaurantDTO;
import com.phongdo.osahaneat.domain.entity.Restaurant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantService {

    Restaurant insertRestaurant(MultipartFile file,
                                String title,
                                String sub_title,
                                String description,
                                boolean is_freeship,
                                String address,
                                String open_time);

    List<RestaurantDTO> getHomepageRestaurant();

    RestaurantDTO getDetailRestaurant(int id);
    Restaurant updateRestaurant(int restaurantId,
                                String title,
                                String sub_title,
                                String description,
                                String address) throws Exception;
    void deleteRestaurant(int restaurantId);

    List<Restaurant> getAllRestaurant();
    List<Restaurant> searchRestaurant(String searchKeyWord);

    Restaurant findRestaurantById(int id);

}
