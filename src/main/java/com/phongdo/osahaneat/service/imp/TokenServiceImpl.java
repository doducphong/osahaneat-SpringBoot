package com.phongdo.osahaneat.service.imp;

import com.phongdo.osahaneat.exception.AppException;
import com.phongdo.osahaneat.exception.ErrorCode;
import com.phongdo.osahaneat.domain.model.TokenPayload;
import com.phongdo.osahaneat.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TokenServiceImpl implements TokenService {
    @Value("${token.access.secret}")
    private String accessTokenSecret;

    @Value("${token.access.duration}")
    private Duration accessTokenDuration;

    @Override
    public String generateAccessToken(TokenPayload payload) {

        Instant now = Instant.now();
        Instant expiration = now.plus(accessTokenDuration);

        payload.setIssuedAt(now);
        payload.setExpiredAt(expiration);

        return Jwts.builder()
                .issuedAt(Date.from(payload.getIssuedAt()))
                .expiration(Date.from(payload.getExpiredAt()))
                .id(payload.getId())
                .claim("userId", payload.getUserId())
                .claim("roles", payload.getRoles())
                .signWith(secretKey()).compact();
    }

    @Override
    public TokenPayload validateAccessToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .verifyWith(secretKey())
                    .build()
                    .parseSignedClaims(token).getPayload();
            return new TokenPayload(
                    claims.getId(),
                    claims.get("userId", Long.class),
                    claims.getIssuedAt().toInstant(),
                    claims.getExpiration().toInstant(),
                    claims.get("roles", List.class)
            );
        } catch (JwtException e) {
            log.error("JwtException | {}", e.getMessage());
            throw new AppException(ErrorCode.UNCATEGORIZED);
        }
    }


    private SecretKey secretKey() {
        return Keys.hmacShaKeyFor(accessTokenSecret.getBytes(StandardCharsets.UTF_8));
    }
}
