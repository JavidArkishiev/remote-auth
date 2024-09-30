package com.example.remoteauth.service;

import com.example.remoteauth.dto.AuthRequest;
import com.example.remoteauth.dto.AuthResponse;
import com.example.remoteauth.dto.UserRequest;
import com.example.remoteauth.entity.User;
import com.example.remoteauth.exception.EmailExistException;
import com.example.remoteauth.exception.UserNotFoundException;
import com.example.remoteauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JWTService jwtService;

    @Autowired
    public void setUserRepository(@Lazy UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired

    public void setAuthenticationManager(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtService(@Lazy JWTService jwtService) {
        this.jwtService = jwtService;
    }

    public void createUser(UserRequest userRequest) throws EmailExistException {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new EmailExistException("This email already used");
        }
        User userEntity = new User();
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setName(userRequest.getName());
        userEntity.setRole(userRequest.getRole());
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userEntity.setSurname(userRequest.getSurname());
        userRepository.save(userEntity);

    }

    public UserDetailsService getEmail() {
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public AuthResponse generateToken(AuthRequest request) {

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword()));

        var accessToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
