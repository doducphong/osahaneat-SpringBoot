package com.phongdo.osahaneat.service;

import com.phongdo.osahaneat.dto.request.SignupRequest;
import com.phongdo.osahaneat.dto.response.UserDTO;
import com.phongdo.osahaneat.entity.Roles;
import com.phongdo.osahaneat.entity.Users;
import com.phongdo.osahaneat.exception.AppException;
import com.phongdo.osahaneat.exception.ErrorCode;
import com.phongdo.osahaneat.mapper.UserMapper;
import com.phongdo.osahaneat.repository.UserRepository;
import com.phongdo.osahaneat.service.imp.UserServiceImp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements UserServiceImp {
    UserRepository userRepository;
    UserMapper userMapper;
    @Override
    public List<UserDTO> getAllUser(){
        List<Users> listUser = userRepository.findAll();
        List<UserDTO> userDTOList = userMapper.toDTOList(listUser);

        return userDTOList;
    }

    @Override
    public UserDTO addUser(SignupRequest signupRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        Roles roles = new Roles();
        roles.setId(signupRequest.getRoleId());

        Users users = new Users();

        if(userRepository.existsByUserName(signupRequest.getUserName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        users.setUserName(signupRequest.getUserName());
        users.setFullname(signupRequest.getFullname());
        String encodePassword = passwordEncoder.encode(signupRequest.getPassword());
        users.setPassword(encodePassword);
        users.setRoles(roles);
        users.setCreateDate(new Date());
        try {
            UserDTO userDTO = userMapper.toDTO(userRepository.save(users));
            return userDTO;
        }catch(Exception e){
            return null;
        }
    }

}
