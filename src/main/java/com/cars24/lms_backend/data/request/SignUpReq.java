package com.cars24.lms_backend.data.request;

import com.cars24.lms_backend.data.enums.UserRoles;
import lombok.Data;


@Data
public class SignUpReq {

    private String name;
    private String phone;
    private String username;
    private String password;
    private UserRoles role;

}
