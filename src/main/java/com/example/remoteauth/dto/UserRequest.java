package com.example.remoteauth.dto;

import com.example.remoteauth.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserRequest {

    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
}
