package com.phongdo.osahaneat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.phongdo.osahaneat.dto.response.ResponseData;
import com.phongdo.osahaneat.service.imp.MenuServiceImpl;
import com.phongdo.osahaneat.service.FileService;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuServiceImpl menuServiceImpl;

    @Autowired
    FileService fileService;

    @PostMapping()
    public ResponseEntity<?> createMenu(
            @RequestParam MultipartFile file,
            @RequestParam String title,
            @RequestParam boolean is_freeship,
            @RequestParam String time_ship,
            @RequestParam double price,
            @RequestParam int cate_id) {

        ResponseData responseData = new ResponseData();
        responseData.setData(menuServiceImpl.createMenu(file, title, is_freeship, time_ship, price, cate_id));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/file/{filename:.+}")
    public ResponseEntity<?> getFileMenu(@PathVariable String filename) {
        Resource resource = fileService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
