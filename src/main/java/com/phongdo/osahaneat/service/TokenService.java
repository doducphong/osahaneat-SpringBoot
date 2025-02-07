package com.phongdo.osahaneat.service;


import com.phongdo.osahaneat.exception.AppException;
import com.phongdo.osahaneat.domain.model.TokenPayload;

public interface TokenService {
    String generateAccessToken(TokenPayload payload);

    TokenPayload validateAccessToken(String token) throws AppException;
}
