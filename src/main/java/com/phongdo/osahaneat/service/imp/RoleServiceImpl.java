package com.phongdo.osahaneat.service.imp;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.phongdo.osahaneat.dto.request.RoleRequest;
import com.phongdo.osahaneat.dto.response.RoleResponse;
import com.phongdo.osahaneat.mapper.RoleMapper;
import com.phongdo.osahaneat.repository.PermissionRepository;
import com.phongdo.osahaneat.repository.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleServiceImpl implements com.phongdo.osahaneat.service.RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    @Override
    public RoleResponse create(RoleRequest request) {
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse> getAll() {

        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    @Override
    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
