package com.phongdo.osahaneat.controller;

import com.nimbusds.jose.JOSEException;
import com.phongdo.osahaneat.dto.request.IntrospectRequest;
import com.phongdo.osahaneat.dto.request.LoginRequest;
import com.phongdo.osahaneat.dto.response.IntrospectResponse;
import com.phongdo.osahaneat.dto.response.LoginResponse;
import com.phongdo.osahaneat.dto.response.UserDTO;
import com.phongdo.osahaneat.dto.response.ApiResponse;
import com.phongdo.osahaneat.dto.request.SignupRequest;
import com.phongdo.osahaneat.service.imp.LoginServiceImp;
import com.phongdo.osahaneat.service.imp.UserServiceImp;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class LoginController {

    LoginServiceImp loginServiceImp;

    UserServiceImp userServiceImp;

    @PostMapping("/signing")
    ApiResponse<LoginResponse> signing(@RequestBody LoginRequest loginRequest){

        var result = loginServiceImp.checkLogin(loginRequest);

        return ApiResponse.<LoginResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/signup")
    ApiResponse<UserDTO> signup(@RequestBody @Valid SignupRequest signupRequest){
        ApiResponse<UserDTO> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userServiceImp.addUser(signupRequest));

        return apiResponse;
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> signup(@RequestBody @Valid IntrospectRequest request)
            throws ParseException, JOSEException {

        var result = loginServiceImp.introspect(request);

        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

}
