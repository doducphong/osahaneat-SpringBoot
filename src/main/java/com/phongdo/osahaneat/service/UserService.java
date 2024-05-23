package com.phongdo.osahaneat.service;

import com.phongdo.osahaneat.dto.request.SignupRequest;
import com.phongdo.osahaneat.dto.request.UserUpdateRequest;
import com.phongdo.osahaneat.dto.response.UserResponse;
import com.phongdo.osahaneat.entity.Role;
import com.phongdo.osahaneat.entity.User;
import com.phongdo.osahaneat.exception.AppException;
import com.phongdo.osahaneat.exception.ErrorCode;
import com.phongdo.osahaneat.mapper.UserMapper;
import com.phongdo.osahaneat.repository.RoleRepository;
import com.phongdo.osahaneat.repository.UserRepository;
import com.phongdo.osahaneat.service.imp.UserServiceImp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService implements UserServiceImp {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUser(){
        log.info("In method get users");
        return userMapper.toUserResponseList(userRepository.findAll());
    }

    @Override
    public UserResponse addUser(SignupRequest signupRequest) {
        Set<String> roleNames = signupRequest.getRoleName();
        Set<Role> roles = new HashSet<>();
        for (String roleName:roleNames) {
            var roleOtp = roleRepository.findByName(roleName).orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
            roles.add(roleOtp);
            
        }

        User user = new User();

        if(userRepository.existsByUserName(signupRequest.getUserName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        user.setUserName(signupRequest.getUserName());
        user.setFullname(signupRequest.getFullname());
        String encodePassword = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(encodePassword);
        user.setRoles(roles);
        user.setCreateDate(new Date());
        try {
            UserResponse userResponse = userMapper.toUserResponse(userRepository.save(user));
            return userResponse;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    @PostAuthorize("returnObject.userName == authentication.name")
    public UserResponse getUser(int id) {
        log.info("In method get user by id");
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() ->
                new AppException((ErrorCode.USER_NOT_EXISTED))));
    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUserName(name).orElseThrow(
                () -> new AppException((ErrorCode.USER_NOT_EXISTED)));

        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(int userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userMapper.updateUserFromRequest(user,request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

}
