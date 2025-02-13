package com.phongdo.osahaneat.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.phongdo.osahaneat.dto.request.PermissionRequest;
import com.phongdo.osahaneat.dto.response.PermissionResponse;
import com.phongdo.osahaneat.domain.entity.Permission;
import com.phongdo.osahaneat.mapper.PermissionMapper;
import com.phongdo.osahaneat.repository.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionServiceImpl implements com.phongdo.osahaneat.service.PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);

        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
