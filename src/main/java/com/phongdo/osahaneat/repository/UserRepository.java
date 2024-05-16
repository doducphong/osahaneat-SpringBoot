package com.phongdo.osahaneat.repository;

import com.phongdo.osahaneat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByUserNameAndPassword(String username, String password);
    Optional<User> findByUserName(String username);
    Boolean existsByUserName(String username);

}
