package com.phongdo.osahaneat.service.imp;

import com.nimbusds.jose.JOSEException;
import com.phongdo.osahaneat.dto.request.IntrospectRequest;
import com.phongdo.osahaneat.dto.request.LoginRequest;
import com.phongdo.osahaneat.dto.response.IntrospectResponse;
import com.phongdo.osahaneat.dto.response.LoginResponse;

import java.text.ParseException;

public interface AuthenticationServiceImp {
    LoginResponse checkLogin(LoginRequest loginRequest);
    IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException;
}
