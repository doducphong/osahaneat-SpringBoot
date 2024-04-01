package com.phongdo.osahaneat.service;

import com.phongdo.osahaneat.dto.CategoryDTO;
import com.phongdo.osahaneat.dto.UserDTO;
import com.phongdo.osahaneat.entity.Category;
import com.phongdo.osahaneat.entity.Roles;
import com.phongdo.osahaneat.entity.Users;
import com.phongdo.osahaneat.repository.UserRepository;
import com.phongdo.osahaneat.security.CustomFilterSecurity;
import com.phongdo.osahaneat.security.CustomJwtFilter;
import com.phongdo.osahaneat.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements UserServiceImp {

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
    public UserDTO createUser(UserDTO userDTO) {
        try{
            Users users = new Users();
            users.setUserName(userDTO.getUserName());
            users.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            users.setFullname(userDTO.getFullname());
            users.setCreateDate(new Date());
            Roles roles = new Roles();
            roles.setId(2);
            users.setRoles(roles);
            userRepository.save(users);
            return convertToDTO(users);
        }catch (Exception e){
            System.out.println("Error create user " + e);
            return null;
        }

    }

    private UserDTO convertToDTO(Users users) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(users.getId());
        userDTO.setUserName(users.getUserName());
        userDTO.setPassword(users.getUserName());
        userDTO.setFullname(users.getFullname());
        userDTO.setCreateDate(users.getCreateDate());

        return userDTO;
    }
}
