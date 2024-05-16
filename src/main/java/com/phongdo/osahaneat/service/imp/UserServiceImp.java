package com.phongdo.osahaneat.service.imp;

import com.phongdo.osahaneat.dto.request.UserUpdateRequest;
import com.phongdo.osahaneat.dto.response.UserResponse;
import com.phongdo.osahaneat.dto.request.SignupRequest;

import java.util.List;

public interface UserServiceImp {
    List<UserResponse> getAllUser();
    UserResponse addUser(SignupRequest signupRequest);

    UserResponse getUser(int id);

    UserResponse getMyInfo();
    UserResponse updateUser(int userId, UserUpdateRequest request);

}
