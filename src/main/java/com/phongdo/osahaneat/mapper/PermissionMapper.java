package com.phongdo.osahaneat.mapper;

import com.phongdo.osahaneat.dto.request.PermissionRequest;
import com.phongdo.osahaneat.dto.response.PermissionResponse;
import com.phongdo.osahaneat.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);



}
