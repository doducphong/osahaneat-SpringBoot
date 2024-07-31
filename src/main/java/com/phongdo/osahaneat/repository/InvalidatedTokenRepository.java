package com.phongdo.osahaneat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phongdo.osahaneat.entity.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
