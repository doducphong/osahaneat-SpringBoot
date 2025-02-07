package com.phongdo.osahaneat.service;

import java.util.List;

import com.phongdo.osahaneat.dto.request.RoleRequest;
import com.phongdo.osahaneat.dto.response.RoleResponse;

public interface RoleService {
    RoleResponse create(RoleRequest request);

    List<RoleResponse> getAll();

    void delete(String role);
}
