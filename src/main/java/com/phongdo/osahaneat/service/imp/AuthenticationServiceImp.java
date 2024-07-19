package com.phongdo.osahaneat.service.imp;

import com.nimbusds.jose.JOSEException;
import com.phongdo.osahaneat.dto.request.IntrospectRequest;
import com.phongdo.osahaneat.dto.request.LoginRequest;
import com.phongdo.osahaneat.dto.request.LogoutRequest;
import com.phongdo.osahaneat.dto.request.RefreshRequest;
import com.phongdo.osahaneat.dto.response.IntrospectResponse;
import com.phongdo.osahaneat.dto.response.AuthenticationResponse;

import java.text.ParseException;

public interface AuthenticationServiceImp {
    AuthenticationResponse checkLogin(LoginRequest loginRequest);
    void logout(LogoutRequest logoutRequest) throws ParseException, JOSEException;
    IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException;
    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}
