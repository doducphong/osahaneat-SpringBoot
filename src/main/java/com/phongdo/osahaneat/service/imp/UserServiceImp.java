package com.phongdo.osahaneat.service.imp;

import com.phongdo.osahaneat.dto.UserDTO;
import com.phongdo.osahaneat.entity.Users;
import com.phongdo.osahaneat.payload.request.SignupRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserServiceImp {
    List<UserDTO> getAllUser();
    Users addUser(SignupRequest signupRequest);

}
