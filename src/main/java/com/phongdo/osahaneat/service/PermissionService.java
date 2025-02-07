package com.phongdo.osahaneat.service;

import java.util.List;

import com.phongdo.osahaneat.dto.request.PermissionRequest;
import com.phongdo.osahaneat.dto.response.PermissionResponse;

public interface PermissionService {
    PermissionResponse create(PermissionRequest request);

    List<PermissionResponse> getAll();

    void delete(String permission);
}
