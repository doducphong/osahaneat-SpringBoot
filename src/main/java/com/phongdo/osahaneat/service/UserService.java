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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService implements UserServiceImp {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> getAllUser(){
        log.info("In method get users");
        return userMapper.toDTOList(userRepository.findAll());
    }

    @Override
    public UserDTO addUser(SignupRequest signupRequest) {
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

    @Override
    @PostAuthorize("returnObject.userName == authentication.name")
    public UserDTO getUser(int id) {
        log.info("In method get user by id");
        return userMapper.toDTO(userRepository.findById(id).orElseThrow(() ->
                new AppException((ErrorCode.USER_NOT_EXISTED))));
    }

    public UserDTO getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        Users user = userRepository.findByUserName(name).orElseThrow(
                () -> new AppException((ErrorCode.USER_NOT_EXISTED)));

        return userMapper.toDTO(user);
    }

}
