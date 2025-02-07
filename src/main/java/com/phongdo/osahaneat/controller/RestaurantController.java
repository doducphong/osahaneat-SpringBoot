package com.phongdo.osahaneat.controller;

import com.phongdo.osahaneat.dto.response.ApiResponse;
import com.phongdo.osahaneat.dto.response.RestaurantDTO;
import com.phongdo.osahaneat.domain.entity.Restaurant;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.phongdo.osahaneat.dto.response.ResponseData;
import com.phongdo.osahaneat.service.FileService;
import com.phongdo.osahaneat.service.RestaurantService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestaurantController {

    FileService fileService;

    RestaurantService restaurantService;

    @PostMapping()
    public ApiResponse<Restaurant> createRestaurant(
            @RequestParam MultipartFile file,
            @RequestParam String title,
            @RequestParam String subTitle,
            @RequestParam String description,
            @RequestParam boolean isFreeship,
            @RequestParam String address,
            @RequestParam String openTime) {
        log.info("File: {}", file.getOriginalFilename());
        log.info("Title: {}", title);
        log.info("SubTitle: {}", subTitle);

        ApiResponse<Restaurant> response = new ApiResponse<>();

        response.setResult(restaurantService
                .insertRestaurant(file, title, subTitle, description, isFreeship, address, openTime));
        return response;
    }

    @GetMapping()
    public ApiResponse<List<RestaurantDTO>> getHomeRestaurant() {
        ApiResponse<List<RestaurantDTO>> response = new ApiResponse<>();
        response.setResult(restaurantService.getHomepageRestaurant());
        return response;
    }

    @PutMapping("/{id}")
    public ApiResponse<Restaurant> updateRestaurant(
            @PathVariable int id,
            @RequestPart String title,
            @RequestPart String subTitle,
            @RequestPart String description,
            @RequestPart String address) throws Exception {

        ApiResponse<Restaurant> response = new ApiResponse<>();

        response.setResult(restaurantService
                .updateRestaurant(id, title, subTitle, description, address));
        return response;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteRestaurant(
            @PathVariable int id
            ) throws Exception {

        ApiResponse<Restaurant> response = new ApiResponse<>();
        response.setMessage("Restaurant deleted successfully");
        restaurantService.deleteRestaurant(id);
        return response;
    }

    @GetMapping("/search")
    public ApiResponse<List<Restaurant>> searchRestaurant(@RequestParam String keyWord) {
        ApiResponse<List<Restaurant>> response = new ApiResponse<>();
        response.setResult(restaurantService.searchRestaurant(keyWord));
        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse<Restaurant> finByRestaurantId(@PathVariable int id) {
        ApiResponse<Restaurant> response = new ApiResponse<>();
        response.setResult(restaurantService.findRestaurantById(id));
        return response;
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileRestaurant(@PathVariable String filename) {
        Resource resource = fileService.loadFile(filename);
        log.info("load file here");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getDetailRestaurant(@RequestParam int id) {
        ResponseData responseData = new ResponseData();
        responseData.setData(restaurantService.getDetailRestaurant(id));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
