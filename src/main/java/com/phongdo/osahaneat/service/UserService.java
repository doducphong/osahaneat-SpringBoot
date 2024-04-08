package com.phongdo.osahaneat.service;

import com.phongdo.osahaneat.dto.CategoryDTO;
import com.phongdo.osahaneat.dto.UserDTO;
import com.phongdo.osahaneat.entity.Category;
import com.phongdo.osahaneat.entity.Roles;
import com.phongdo.osahaneat.entity.Users;
import com.phongdo.osahaneat.payload.request.SignupRequest;
import com.phongdo.osahaneat.repository.RoleRepository;
import com.phongdo.osahaneat.repository.UserRepository;
import com.phongdo.osahaneat.security.CustomFilterSecurity;
import com.phongdo.osahaneat.security.CustomJwtFilter;
import com.phongdo.osahaneat.service.imp.UserServiceImp;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserServiceImp {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository repository;
    @Override
    public List<UserDTO> getAllUser(){
        List<Users> listUser = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Users users:listUser) {
            UserDTO userDTO = modelMapper.map(users,UserDTO.class);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public Users addUser(SignupRequest signupRequest) {

        Roles roles = new Roles();
        roles.setId(signupRequest.getRoleId());
        ModelMapper modelMapper = new ModelMapper();

        Users users = new Users();

        users.setUserName(signupRequest.getUserName());
        users.setFullname(signupRequest.getFullname());
        String encodePassword = passwordEncoder.encode(signupRequest.getPassword());
        users.setPassword(encodePassword);
        users.setRoles(roles);
        users.setCreateDate(new Date());
        try {
            userRepository.save(users);
            return users;
        }catch(Exception e){
            return null;
        }
    }

}
