package com.example.remoteauth.controller;

import com.example.remoteauth.dto.AuthRequest;
import com.example.remoteauth.dto.AuthResponse;
import com.example.remoteauth.dto.UserRequest;
import com.example.remoteauth.exception.EmailExistException;
import com.example.remoteauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) throws EmailExistException {
        userService.createUser(userRequest);
        return ResponseEntity.ok("User created");
    }

    @PostMapping("token")
    public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest request) {
        return new ResponseEntity<>(userService.generateToken(request), HttpStatus.OK);
    }
}
