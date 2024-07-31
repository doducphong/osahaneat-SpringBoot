package com.phongdo.osahaneat.service.imp;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.phongdo.osahaneat.dto.response.RestaurantDTO;

public interface RestaurantServiceImp {

    boolean insertRestaurant(
            MultipartFile file,
            String title,
            String sub_title,
            String description,
            boolean is_freeship,
            String address,
            String open_time);

    List<RestaurantDTO> getHomepageRestaurant();

    RestaurantDTO getDetailRestaurant(int id);
}
