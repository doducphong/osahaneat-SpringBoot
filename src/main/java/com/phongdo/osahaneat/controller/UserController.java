package com.phongdo.osahaneat.controller;

import com.phongdo.osahaneat.dto.response.ApiResponse;
import com.phongdo.osahaneat.dto.response.UserDTO;
import com.phongdo.osahaneat.repository.UserRepository;
import com.phongdo.osahaneat.service.imp.UserServiceImp;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {

    UserServiceImp userServiceImp;

    @GetMapping("")
    public ApiResponse<List<UserDTO>> getAllUser(){

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}",authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserDTO>>builder()
                .result(userServiceImp.getAllUser())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserDTO> getUser(@PathVariable("userId") int userId){
        return ApiResponse.<UserDTO>builder()
                .result(userServiceImp.getUser(userId))
                .build();
    }

    @GetMapping("/myInfo")
    ApiResponse<UserDTO> getMyInfo(){
        return ApiResponse.<UserDTO>builder()
                .result(userServiceImp.getMyInfo())
                .build();
    }

}
