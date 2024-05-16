package com.phongdo.osahaneat.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    int id;
    String userName;
    String password;
    String fullname;
    Date createDate;
    Set<RoleResponse> roles;



}
