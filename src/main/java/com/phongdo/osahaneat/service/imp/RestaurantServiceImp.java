package com.phongdo.osahaneat.service.imp;

import com.phongdo.osahaneat.dto.response.RestaurantDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantServiceImp {

    boolean insertRestaurant( MultipartFile file
                            , String title
                            , String sub_title
                            , String description
                            , boolean is_freeship
                            , String address
                            , String open_time);
    List<RestaurantDTO> getHomepageRestaurant();
    RestaurantDTO getDetailRestaurant(int id);
}
