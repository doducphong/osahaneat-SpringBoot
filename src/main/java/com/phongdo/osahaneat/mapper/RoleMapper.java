package com.phongdo.osahaneat.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.phongdo.osahaneat.dto.request.RoleRequest;
import com.phongdo.osahaneat.dto.response.RoleResponse;
import com.phongdo.osahaneat.domain.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
