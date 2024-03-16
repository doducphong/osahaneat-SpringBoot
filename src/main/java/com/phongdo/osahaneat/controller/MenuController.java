package com.phongdo.osahaneat.controller;

import com.phongdo.osahaneat.entity.Menu;
import com.phongdo.osahaneat.payload.ResponseData;
import com.phongdo.osahaneat.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;
    @PostMapping()
    public ResponseEntity<?> createMenu(
            @RequestParam MultipartFile file,
            @RequestParam String title,
            @RequestParam boolean is_freeship,
            @RequestParam String time_ship,
            @RequestParam double price,
            @RequestParam int cate_id
            ){

        ResponseData responseData = new ResponseData();
        menuService.createMenu(file,title,is_freeship,time_ship,price,cate_id);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
