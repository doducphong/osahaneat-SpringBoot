package com.phongdo.osahaneat.domain.model;

import com.phongdo.osahaneat.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Data
public class TokenPayload {
    private String id;
    private Long userId;
    private Instant issuedAt;
    private Instant expiredAt;
    private List<Role> roles;
    public TokenPayload(String id, Long userId) {
        this.id = id;
        this.userId = userId;
    }
}
