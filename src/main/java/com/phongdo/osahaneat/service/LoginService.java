package com.phongdo.osahaneat.service;

import com.phongdo.osahaneat.dto.UserDTO;
import com.phongdo.osahaneat.entity.Roles;
import com.phongdo.osahaneat.entity.Users;
import com.phongdo.osahaneat.payload.request.SignupRequest;
import com.phongdo.osahaneat.repository.UserRepository;
import com.phongdo.osahaneat.service.imp.LoginServiceImp;
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
    public List<UserDTO> getAllUser(){
        List<Users> listUser = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (Users users:listUser) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(users.getUserName());
            userDTO.setFullname(users.getFullname());
            userDTO.setId(users.getId());
            userDTO.setCreateDate(users.getCreateDate());
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        Users user = userRepository.findByUserName(username);
         return passwordEncoder.matches(password,user.getPassword());

    }

    @Override
    public boolean addUser(SignupRequest signupRequest) {


        Roles roles = new Roles();
        roles.setId(signupRequest.getRoleId());

        Users users = new Users();
        users.setFullname(signupRequest.getFullname());
        users.setUserName(signupRequest.getEmail());
        String encodePassword = passwordEncoder.encode(signupRequest.getPassword());
        users.setPassword(encodePassword);
        users.setRoles(roles);
        users.setCreateDate(new Date());
        try {
            userRepository.save(users);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
