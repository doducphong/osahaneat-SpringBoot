package com.phongdo.osahaneat.mapper;

import org.mapstruct.Mapper;

import com.phongdo.osahaneat.dto.request.PermissionRequest;
import com.phongdo.osahaneat.dto.response.PermissionResponse;
import com.phongdo.osahaneat.domain.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
