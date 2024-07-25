package com.phongdo.osahaneat.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SignupRequest {

    String fullname;
    //@Email(message = "username must be email")
    @Size(min = 4,message = "USERNAME_INVALID")
    String userName;

    @Size(min = 8,message = "PASSWORD_INVALID")
    String password;
    Set<String> roleName;


}
