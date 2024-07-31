package com.phongdo.osahaneat.dto.response;

import java.util.Date;
import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    int id;
    String userName;
    String fullname;
    Date createDate;
    Set<RoleResponse> roles;
}
