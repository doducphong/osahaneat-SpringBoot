package com.phongdo.osahaneat.service;

import java.util.List;

import com.phongdo.osahaneat.dto.request.SignupRequest;
import com.phongdo.osahaneat.dto.request.UserUpdateRequest;
import com.phongdo.osahaneat.dto.response.UserResponse;

public interface UserService {
    List<UserResponse> getAllUser();

    UserResponse addUser(SignupRequest signupRequest);

    UserResponse getUser(int id);

    UserResponse getMyInfo();

    UserResponse updateUser(int userId, UserUpdateRequest request);
}
