package com.phongdo.osahaneat.service;

import com.phongdo.osahaneat.dto.UserDTO;
import com.phongdo.osahaneat.entity.Roles;
import com.phongdo.osahaneat.entity.Users;
import com.phongdo.osahaneat.payload.request.SignupRequest;
import com.phongdo.osahaneat.repository.UserRepository;
import com.phongdo.osahaneat.service.imp.LoginServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Service
public class LoginService implements LoginServiceImp {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean checkLogin(String username, String password) {
        Users user = userRepository.findByUserName(username);
         return passwordEncoder.matches(password,user.getPassword());

    }


}
