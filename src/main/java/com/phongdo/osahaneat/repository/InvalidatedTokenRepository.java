package com.phongdo.osahaneat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phongdo.osahaneat.domain.entity.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
