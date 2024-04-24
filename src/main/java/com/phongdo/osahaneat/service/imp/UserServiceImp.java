package com.phongdo.osahaneat.service.imp;

import com.phongdo.osahaneat.dto.response.UserDTO;
import com.phongdo.osahaneat.dto.request.SignupRequest;

import java.util.List;

public interface UserServiceImp {
    List<UserDTO> getAllUser();
    UserDTO addUser(SignupRequest signupRequest);

}
