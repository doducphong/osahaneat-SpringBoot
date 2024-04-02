package com.phongdo.osahaneat.repository;

import com.phongdo.osahaneat.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByRoleName(String name);
}
