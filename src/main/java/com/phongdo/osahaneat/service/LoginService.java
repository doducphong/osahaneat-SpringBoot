package com.phongdo.osahaneat.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.phongdo.osahaneat.dto.request.IntrospectRequest;
import com.phongdo.osahaneat.dto.request.LoginRequest;
import com.phongdo.osahaneat.dto.response.IntrospectResponse;
import com.phongdo.osahaneat.dto.response.LoginResponse;
import com.phongdo.osahaneat.entity.Users;
import com.phongdo.osahaneat.exception.AppException;
import com.phongdo.osahaneat.exception.ErrorCode;
import com.phongdo.osahaneat.repository.UserRepository;
import com.phongdo.osahaneat.service.imp.LoginServiceImp;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class LoginService implements LoginServiceImp {

    UserRepository userRepository;
    @NonFinal
    @Value("${jwt.privateKey}")
    protected String SIGNER_KEY;
    PasswordEncoder passwordEncoder;
    @Override
    public LoginResponse checkLogin(LoginRequest loginRequest) {
        var user = userRepository.findByUserName(loginRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

         boolean issSuccess =  passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());

         if(!issSuccess){
             throw new AppException(ErrorCode.UNAUTHENTICATED);

         }
         var token = generateToken(user);
         return LoginResponse.builder()
                 .logged(true)
                 .token(token)
                 .build();

    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest introspectRequest)
            throws JOSEException, ParseException {
        var token = introspectRequest.getToken();
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && expityTime.after(new Date()))
                .build();
    }


    private String generateToken(Users users){

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(users.getUserName())
                .issuer("phongdo.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.DAYS).toEpochMilli()
                ))
                .claim("scope", buiScope(users))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header,payload);

        try{
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }

    private String buiScope(Users users){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(Collections.singleton(users.getRoles().getRoleName()))){
            stringJoiner.add(users.getRoles().getRoleName());
        }
        return stringJoiner.toString();
    }


}
