package com.phongdo.osahaneat.service.imp;

import com.phongdo.osahaneat.dto.request.PermissionRequest;
import com.phongdo.osahaneat.dto.response.PermissionResponse;
import com.phongdo.osahaneat.entity.Permission;

import java.util.List;

public interface PermissionServiceImp {
    PermissionResponse create(PermissionRequest request);
    List<PermissionResponse> getAll();
    void delete(String permission);
}
