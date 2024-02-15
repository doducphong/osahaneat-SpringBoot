package com.phongdo.osahaneat.service;

import com.phongdo.osahaneat.dto.UserDTO;
import com.phongdo.osahaneat.entity.Users;
import com.phongdo.osahaneat.repository.UserRepository;
import com.phongdo.osahaneat.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements LoginServiceImp {

    @Autowired
    UserRepository userRepository;
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

            System.out.println(users.getFullname());

            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        List<Users> usersList = userRepository.findByUserNameAndPassword(username,password);

        return usersList.size()>0;
    }
}
