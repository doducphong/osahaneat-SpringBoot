package com.phongdo.osahaneat.service.imp;

import java.text.SimpleDateFormat;
import java.util.*;

import com.phongdo.osahaneat.domain.entity.MenuRestaurant;
import com.phongdo.osahaneat.domain.entity.RatingRestaurant;
import com.phongdo.osahaneat.domain.entity.Restaurant;
import com.phongdo.osahaneat.exception.AppException;
import com.phongdo.osahaneat.exception.ErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.phongdo.osahaneat.dto.response.CategoryDTO;
import com.phongdo.osahaneat.dto.response.MenuDTO;
import com.phongdo.osahaneat.dto.response.RestaurantDTO;
import com.phongdo.osahaneat.mapper.CategoryMapper;
import com.phongdo.osahaneat.mapper.RestaurantMapper;
import com.phongdo.osahaneat.repository.RestaurantRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestaurantServiceImpl implements com.phongdo.osahaneat.service.RestaurantService {

    FileServiceImpl fileServiceImpl;

    RestaurantRepository restaurantRepository;

    RestaurantMapper restaurantMapper;

    CategoryMapper categoryMapper;

    @Override
    public Restaurant insertRestaurant(MultipartFile file,
                                       String title,
                                        String subTitle,
                                        String description,
                                        boolean isFreeship,
                                        String address,
                                        String openTime) {
        Restaurant restaurant = new Restaurant();
        try {
                restaurant.setTitle(title);
                restaurant.setSubTitle(subTitle);
                restaurant.setDesc(description);
                restaurant.setImage(fileServiceImpl.uploadFile(file));
                restaurant.setFreeship(isFreeship);
                restaurant.setAddress(address);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date openDate = simpleDateFormat.parse(openTime);

                restaurant.setOpenTime(openDate);
                restaurantRepository.save(restaurant);
        } catch (Exception e) {
            System.out.println("Error insert restaurant" + e);
        }
        return restaurant;
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

    @Override
    public Restaurant updateRestaurant(int restaurantId, String title, String sub_title, String description,String address)throws Exception {
        Restaurant restaurant = restaurantRepository.findRestaurantById(restaurantId);

        if(restaurant.getTitle()!=null){
            restaurant.setTitle(title);
        }
        if(restaurant.getSubTitle()!=null){
            restaurant.setSubTitle(sub_title);
        }
        if(restaurant.getDesc()!=null){
            restaurant.setDesc(description);
        }
        if(restaurant.getAddress()!=null){
            restaurant.setAddress(address);
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(int restaurantId){
        try {
            restaurantRepository.deleteById(restaurantId);
        }catch (Exception e){
            throw new AppException(ErrorCode.RESTAURANT_NOT_EXISTED);
        }
    }

    @Override
    public List<Restaurant> getAllRestaurant() {

        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String searchKeyWord) {
        return restaurantRepository.findBySearchQuery(searchKeyWord);
    }

    @Override
    public Restaurant findRestaurantById(int id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.RESTAURANT_NOT_EXISTED));
    }
}
