package com.phongdo.osahaneat.dto.response;

import com.phongdo.osahaneat.entity.Roles;
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
public class UserDTO {

    int id;
    String userName;
    String password;
    String fullname;
    Date createDate;
    //Set<String> listRole;



}
