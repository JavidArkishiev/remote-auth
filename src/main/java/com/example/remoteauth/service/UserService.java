package com.example.remoteauth.service;

import com.example.remoteauth.dto.AuthRequest;
import com.example.remoteauth.dto.AuthResponse;
import com.example.remoteauth.dto.UserRequest;
import com.example.remoteauth.exception.EmailExistException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    void createUser(UserRequest userRequest) throws EmailExistException;

    UserDetailsService getEmail();

    AuthResponse generateToken(AuthRequest request);
}
