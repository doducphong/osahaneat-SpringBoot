package com.phongdo.osahaneat.service.imp;

import com.phongdo.osahaneat.dto.UserDTO;

import java.util.List;

public interface LoginServiceImp {
    List<UserDTO> getAllUser();
    boolean checkLogin(String username, String password);
}
