package com.phongdo.osahaneat.service;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.phongdo.osahaneat.dto.response.CategoryDTO;
import com.phongdo.osahaneat.dto.response.MenuDTO;
import com.phongdo.osahaneat.dto.response.RestaurantDTO;
import com.phongdo.osahaneat.entity.*;
import com.phongdo.osahaneat.mapper.CategoryMapper;
import com.phongdo.osahaneat.mapper.RestaurantMapper;
import com.phongdo.osahaneat.repository.RestaurantRepository;
import com.phongdo.osahaneat.service.imp.FileServiceImp;
import com.phongdo.osahaneat.service.imp.RestaurantServiceImp;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestaurantService implements RestaurantServiceImp {

    FileServiceImp fileServiceImp;

    RestaurantRepository restaurantRepository;

    RestaurantMapper restaurantMapper;

    CategoryMapper categoryMapper;

    @Override
    public boolean insertRestaurant(
            MultipartFile file,
            String title,
            String sub_title,
            String description,
            boolean is_freeship,
            String address,
            String open_time) {
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
        } catch (Exception e) {
            System.out.println("Error insert restaurant" + e);
        }
        return isInsertSuccess;
    }

    @Override
    public List<RestaurantDTO> getHomepageRestaurant() {
        List<RestaurantDTO> restaurantDTOS = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0, 6);
        Page<Restaurant> listData = restaurantRepository.findAll(pageRequest);
        for (Restaurant data : listData) {
            RestaurantDTO restaurantDTO = restaurantMapper.restaurantToRestaurantDTO(data);
            restaurantDTO.setRating(calculatorRating(data.getListRatingRestaurant()));
            List<CategoryDTO> categoryDTOList = new ArrayList<>();
            for (MenuRestaurant menuRestaurant : data.getListMenu()) {
                CategoryDTO categoryDTO = categoryMapper.toDTO(menuRestaurant.getCategory());
                categoryDTOList.add(categoryDTO);
            }
            restaurantDTO.setCategoryDTOList(categoryDTOList);
            restaurantDTOS.add(restaurantDTO);
        }
        return restaurantDTOS;
    }

    public double calculatorRating(Set<RatingRestaurant> listRating) {
        double totalPoint = 0;
        for (RatingRestaurant data : listRating) {
            totalPoint += data.getRatePoint();
        }
        return listRating.isEmpty() ? 0 : totalPoint / listRating.size();
    }

    @Override
    public RestaurantDTO getDetailRestaurant(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        if (restaurant.isPresent()) {
            Restaurant data = restaurant.get();
            restaurantDTO = restaurantMapper.restaurantToRestaurantDTO(data);
            restaurantDTO.setRating(calculatorRating(data.getListRatingRestaurant()));
            List<CategoryDTO> categoryDTOList = new ArrayList<>();
            // category
            for (MenuRestaurant menuRestaurant : data.getListMenu()) {
                List<MenuDTO> menuDTOList = new ArrayList<>();
                CategoryDTO categoryDTO = categoryMapper.toDTO(menuRestaurant.getCategory());
                // menu
                categoryDTOList.add(categoryDTO);
            }
            restaurantDTO.setCategoryDTOList(categoryDTOList);
        }

        return restaurantDTO;
    }
}
