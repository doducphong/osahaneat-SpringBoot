package com.phongdo.osahaneat.service.imp;

import com.phongdo.osahaneat.dto.UserDTO;
import com.phongdo.osahaneat.payload.request.SignupRequest;

import java.util.List;

public interface LoginServiceImp {
    List<UserDTO> getAllUser();
    boolean checkLogin(String username, String password);
    boolean addUser(SignupRequest signupRequest);
}
