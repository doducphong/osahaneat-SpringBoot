package com.phongdo.osahaneat.controller;

import java.text.ParseException;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.nimbusds.jose.JOSEException;
import com.phongdo.osahaneat.dto.request.*;
import com.phongdo.osahaneat.dto.response.ApiResponse;
import com.phongdo.osahaneat.dto.response.AuthenticationResponse;
import com.phongdo.osahaneat.dto.response.IntrospectResponse;
import com.phongdo.osahaneat.dto.response.UserResponse;
import com.phongdo.osahaneat.service.AuthenticationService;
import com.phongdo.osahaneat.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationController {

    AuthenticationService authenticationService;

    UserService userService;

    @PostMapping("/signing")
    ApiResponse<AuthenticationResponse> signing(@RequestBody LoginRequest loginRequest) {
        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
        var result = authenticationService.checkLogin(loginRequest);
        apiResponse.setResult(result);
        return apiResponse;
    }

    @PostMapping("/signup")
    ApiResponse<UserResponse> signup(@RequestBody @Valid SignupRequest signupRequest) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.addUser(signupRequest));

        return apiResponse;
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody @Valid IntrospectRequest request)
            throws ParseException, JOSEException {

        var result = authenticationService.introspect(request);

        return ApiResponse.<IntrospectResponse>builder().result(result).build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody @Valid LogoutRequest request) throws ParseException, JOSEException {

        authenticationService.logout(request);

        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {

        var result = authenticationService.refreshToken(request);

        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }
}
