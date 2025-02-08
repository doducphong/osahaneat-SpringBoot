package com.phongdo.osahaneat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phongdo.osahaneat.domain.entity.Role;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(String name);
    @Query(
            value = """
                    SELECT r.*
                    FROM roles r
                    INNER JOIN users_roles ur ON r.id = ur.roles_name
                    WHERE ur.users_id = :userId
                    """,
            nativeQuery = true

    )
    List<Role> findAllByUserId(int userId);
}
