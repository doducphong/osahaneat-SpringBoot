package com.phongdo.osahaneat.service;

import java.text.ParseException;

import com.nimbusds.jose.JOSEException;
import com.phongdo.osahaneat.dto.request.IntrospectRequest;
import com.phongdo.osahaneat.dto.request.LoginRequest;
import com.phongdo.osahaneat.dto.request.LogoutRequest;
import com.phongdo.osahaneat.dto.request.RefreshRequest;
import com.phongdo.osahaneat.dto.response.AuthenticationResponse;
import com.phongdo.osahaneat.dto.response.IntrospectResponse;

public interface AuthenticationService {
    AuthenticationResponse checkLogin(LoginRequest loginRequest);

    void logout(LogoutRequest logoutRequest) throws ParseException, JOSEException;

    IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException;

    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}
