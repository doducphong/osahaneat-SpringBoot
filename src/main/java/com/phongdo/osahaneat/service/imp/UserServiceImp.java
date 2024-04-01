package com.phongdo.osahaneat.service.imp;

import com.phongdo.osahaneat.dto.UserDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserServiceImp {
    List<UserDTO> getAllUser();
    UserDTO createUser(@RequestBody UserDTO userDTO);
}
