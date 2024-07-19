package com.phongdo.osahaneat.controller;

import com.nimbusds.jose.JOSEException;
import com.phongdo.osahaneat.dto.request.IntrospectRequest;
import com.phongdo.osahaneat.dto.request.LoginRequest;
import com.phongdo.osahaneat.dto.request.LogoutRequest;
import com.phongdo.osahaneat.dto.response.IntrospectResponse;
import com.phongdo.osahaneat.dto.response.LoginResponse;
import com.phongdo.osahaneat.dto.response.UserResponse;
import com.phongdo.osahaneat.dto.response.ApiResponse;
import com.phongdo.osahaneat.dto.request.SignupRequest;
import com.phongdo.osahaneat.service.imp.AuthenticationServiceImp;
import com.phongdo.osahaneat.service.imp.UserServiceImp;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {

    AuthenticationServiceImp authenticationServiceImp;

    UserServiceImp userServiceImp;

    @PostMapping("/signing")
    ApiResponse<LoginResponse> signing(@RequestBody LoginRequest loginRequest){

        var result = authenticationServiceImp.checkLogin(loginRequest);

        return ApiResponse.<LoginResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/signup")
    ApiResponse<UserResponse> signup(@RequestBody @Valid SignupRequest signupRequest){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userServiceImp.addUser(signupRequest));

        return apiResponse;
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody @Valid IntrospectRequest request)
            throws ParseException, JOSEException {

        var result = authenticationServiceImp.introspect(request);

        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody @Valid LogoutRequest request)
            throws ParseException, JOSEException {

        authenticationServiceImp.logout(request);

        return ApiResponse.<Void>builder()
                .build();
    }

}
