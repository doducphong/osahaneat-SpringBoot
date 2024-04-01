package com.phongdo.osahaneat.controller;

import com.phongdo.osahaneat.dto.UserDTO;
import com.phongdo.osahaneat.entity.Users;
import com.phongdo.osahaneat.payload.ResponseData;
import com.phongdo.osahaneat.service.UserService;
import com.phongdo.osahaneat.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllUser(){

        return new ResponseEntity<>(userServiceImp.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        ResponseData responseData = new ResponseData();

        responseData.setData(userServiceImp.createUser(userDTO));
        return new ResponseEntity<>(responseData, HttpStatus.OK);

    }
}
