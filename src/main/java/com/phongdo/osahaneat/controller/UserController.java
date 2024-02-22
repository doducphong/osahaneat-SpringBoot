package com.phongdo.osahaneat.controller;

import com.phongdo.osahaneat.service.UserService;
import com.phongdo.osahaneat.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllUser(){

        return new ResponseEntity<>(userServiceImp.getAllUser(), HttpStatus.OK);
    }

}