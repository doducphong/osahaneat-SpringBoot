package com.phongdo.osahaneat.controller;

import com.phongdo.osahaneat.payload.ResponseData;
import com.phongdo.osahaneat.repository.UserRepository;
import com.phongdo.osahaneat.service.LoginService;
import com.phongdo.osahaneat.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginServiceImp loginServiceImp;

    @PostMapping("/sigin")
    public ResponseEntity<?> sigin(@RequestParam String username, @RequestParam String password){
        ResponseData responseData = new ResponseData();
        if(loginServiceImp.checkLogin(username,password)){
            responseData.setData("true");
        }
        else {
            responseData.setData("false");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
