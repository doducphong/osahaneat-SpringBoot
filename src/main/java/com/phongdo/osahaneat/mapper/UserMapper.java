package com.phongdo.osahaneat.mapper;

import com.phongdo.osahaneat.dto.response.UserDTO;
import com.phongdo.osahaneat.entity.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users toEntity(UserDTO userDTO);

    UserDTO toDTO(Users users);

    List<UserDTO> toDTOList(List<Users> users);


}
