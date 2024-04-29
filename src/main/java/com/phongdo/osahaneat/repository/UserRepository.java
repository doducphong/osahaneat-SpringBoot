package com.phongdo.osahaneat.repository;

import com.phongdo.osahaneat.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    List<Users> findByUserNameAndPassword(String username, String password);
    Optional<Users> findByUserName(String username);
    Boolean existsByUserName(String username);

}
