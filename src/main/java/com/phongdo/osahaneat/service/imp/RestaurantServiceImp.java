package com.phongdo.osahaneat.service.imp;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface RestaurantServiceImp {

    boolean insertRestaurant( MultipartFile file
                            , String title
                            , String sub_title
                            , String description
                            , boolean is_freeship
                            , String address
                            , String open_time);
}
