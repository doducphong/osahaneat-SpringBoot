package com.phongdo.osahaneat.payload.request;

import com.phongdo.osahaneat.exception.ErrorCode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class SignupRequest {
    private String fullname;
    //@Email(message = "username must be email")
    @Size(min = 3,message = "USERNAME_INVALID")
    private String userName;

    @Size(min = 8,message = "PASSWORD_INVALID")
    private String password;
    private int roleId;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String email) {
        this.userName = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
