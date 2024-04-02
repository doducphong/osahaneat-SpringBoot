package com.phongdo.osahaneat.controller;

import com.phongdo.osahaneat.payload.ResponseData;
import com.phongdo.osahaneat.payload.request.SignupRequest;
import com.phongdo.osahaneat.repository.RoleRepository;
import com.phongdo.osahaneat.repository.UserRepository;
import com.phongdo.osahaneat.service.LoginService;
import com.phongdo.osahaneat.service.imp.LoginServiceImp;
import com.phongdo.osahaneat.utils.JwtUtilsHelper;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.beans.Encoder;
import java.util.Base64;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginServiceImp loginServiceImp;

    @Autowired
    JwtUtilsHelper jwtUtilsHelper;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password){
        ResponseData responseData = new ResponseData();

        if(loginServiceImp.checkLogin(username,password)){
            String token = jwtUtilsHelper.generateToken(username);
            responseData.setData(token);

        }
        else {
            responseData.setData(null);
            responseData.setSuccess(false);

        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){

        if(userRepository.existsByuserName(signupRequest.getEmail())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        boolean allowLocal = false;
        if (signupRequest.getEmail() == null || signupRequest.getEmail().isEmpty()) {
            return new ResponseEntity<>("Username is empty!", HttpStatus.BAD_REQUEST);
        } else {
            boolean isValidEmail = EmailValidator.getInstance(allowLocal).isValid(signupRequest.getEmail());
            if (!isValidEmail) {
                return new ResponseEntity<>("Username must Email!", HttpStatus.BAD_REQUEST);
            }
        }
        ResponseData responseData = new ResponseData();
        responseData.setData(loginServiceImp.addUser(signupRequest));
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


}
