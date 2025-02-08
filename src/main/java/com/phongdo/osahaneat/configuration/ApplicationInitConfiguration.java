package com.phongdo.osahaneat.configuration;

import com.phongdo.osahaneat.domain.entity.Role;
import com.phongdo.osahaneat.domain.entity.User;
import com.phongdo.osahaneat.repository.RoleRepository;
import com.phongdo.osahaneat.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
@Order(2)
public class ApplicationInitConfiguration implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Value("${user-admin.email}")
    private String adminEmail;

    @Value("${user-admin.password}")
    private String adminPassword;

    public ApplicationInitConfiguration(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = userRepository.findByUserName(adminEmail).orElse(
                User.builder().userName(adminEmail).password(passwordEncoder.encode(adminPassword)).build()
        );
        userRepository.save(user);

        List<Role> roles = roleRepository.findAll();

        List<Role> userRoles = roleRepository.findAllByUserId(user.getId());
        Set<String> existingRoleIds = userRoles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        List<Role> newUserRoles = roles.stream()
                .filter(role -> !existingRoleIds.contains(role.getName())) // Lọc các role chưa có
                .map(role -> Role.builder().name(role.getName()).build()) // Tạo UserRole mới
                .toList();

        if (!newUserRoles.isEmpty()) {
            roleRepository.saveAll(newUserRoles);
        }
    }
}
