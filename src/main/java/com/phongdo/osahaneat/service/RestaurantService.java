package com.phongdo.osahaneat.service;

import com.phongdo.osahaneat.dto.CategoryDTO;
import com.phongdo.osahaneat.dto.MenuDTO;
import com.phongdo.osahaneat.dto.RestaurantDTO;
import com.phongdo.osahaneat.entity.*;
import com.phongdo.osahaneat.repository.RestaurantRepository;
import com.phongdo.osahaneat.service.imp.FileServiceImp;
import com.phongdo.osahaneat.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;
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
            restaurantDTO.setId(data.getId());
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

    @Override
    public RestaurantDTO getDetailRestaurant(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        RestaurantDTO restaurantDTO = new RestaurantDTO();

        if(restaurant.isPresent()){
            Restaurant data = restaurant.get();
            restaurantDTO.setImage(data.getImage());
            restaurantDTO.setOpenDate(data.getOpenTime());
            restaurantDTO.setTitle(data.getTitle());
            restaurantDTO.setSubtitle(data.getSubTitle());
            restaurantDTO.setFreeShip(data.isFreeship());
            restaurantDTO.setRating(calculatorRating(data.getListRatingRestaurant()));
            restaurantDTO.setDesc(data.getDesc());
            List<CategoryDTO> categoryDTOList = new ArrayList<>();
            //category
            for (MenuRestaurant menuRestaurant : data.getListMenu()) {
                List<MenuDTO> menuDTOList = new ArrayList<>();
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setName(menuRestaurant.getCategory().getNameCate());
                //menu
                for (Food food:menuRestaurant.getCategory().getListFood()) {
                    MenuDTO menuDTO = new MenuDTO();
                    menuDTO.setId(food.getId());
                    menuDTO.setTitle(food.getTitle());
                    menuDTO.setImage(food.getImage());
                    menuDTO.setFreeShip(food.isFreeShip());
                    menuDTO.setDesc(food.getDesc());
                    menuDTO.setPrice(food.getPrice());
                    menuDTOList.add(menuDTO);
                }
                categoryDTO.setMenus(menuDTOList);
                categoryDTOList.add(categoryDTO);
            }
            restaurantDTO.setCategoryDTOList(categoryDTOList);
        }

        return restaurantDTO;
    }
}
