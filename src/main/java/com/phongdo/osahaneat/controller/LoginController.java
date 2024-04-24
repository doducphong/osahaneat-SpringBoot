package com.phongdo.osahaneat.controller;

import com.phongdo.osahaneat.dto.response.UserDTO;
import com.phongdo.osahaneat.dto.response.ResponseData;
import com.phongdo.osahaneat.dto.request.ApiResponse;
import com.phongdo.osahaneat.dto.request.SignupRequest;
import com.phongdo.osahaneat.repository.UserRepository;
import com.phongdo.osahaneat.service.imp.LoginServiceImp;
import com.phongdo.osahaneat.service.imp.UserServiceImp;
import com.phongdo.osahaneat.utils.JwtUtilsHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginServiceImp loginServiceImp;

    @Autowired
    JwtUtilsHelper jwtUtilsHelper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServiceImp userServiceImp;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password){
        ResponseData responseData = new ResponseData();

        if(loginServiceImp.checkLogin(username,password)){
            String token = jwtUtilsHelper.generateToken(username);
            responseData.setData(token);

        }
        else {
            responseData.setData(null);
            responseData.setSuccess(false);

        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ApiResponse<UserDTO> signup(@RequestBody @Valid SignupRequest signupRequest){
        ApiResponse<UserDTO> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userServiceImp.addUser(signupRequest));

        return apiResponse;
    }


}
