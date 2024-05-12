package com.phongdo.osahaneat.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

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
