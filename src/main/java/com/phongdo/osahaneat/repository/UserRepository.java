package com.phongdo.osahaneat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phongdo.osahaneat.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByUserNameAndPassword(String username, String password);

    Optional<User> findByUserName(String username);

    Boolean existsByUserName(String username);
}
