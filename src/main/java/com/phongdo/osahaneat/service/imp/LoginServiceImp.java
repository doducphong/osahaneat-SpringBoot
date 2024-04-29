package com.phongdo.osahaneat.service.imp;

import com.phongdo.osahaneat.dto.request.LoginRequest;
import com.phongdo.osahaneat.dto.response.LoginResponse;

public interface LoginServiceImp {
    LoginResponse checkLogin(LoginRequest loginRequest);
}
