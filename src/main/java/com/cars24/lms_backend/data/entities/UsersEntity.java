package com.cars24.lms_backend.data.entities;


import com.cars24.lms_backend.data.enums.UserRoles;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "users")
@RequiredArgsConstructor
public class UsersEntity {

    @Id
    private String Id;

    private String name;

    private String phone;

    private  String username;

    private  String password;

    private UserRoles roles;

}
