package com.phongdo.osahaneat.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.phongdo.osahaneat.dto.request.SignupRequest;
import com.phongdo.osahaneat.dto.request.UserUpdateRequest;
import com.phongdo.osahaneat.dto.response.UserResponse;
import com.phongdo.osahaneat.domain.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    User toEntity(SignupRequest request);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);

    @Mapping(target = "roles", ignore = true)
    void updateUserFromRequest(@MappingTarget User user, UserUpdateRequest request);
}
