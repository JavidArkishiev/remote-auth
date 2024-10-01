package com.example.remoteauth.service;

import com.example.remoteauth.entity.Transaction;
import com.example.remoteauth.repository.TransactionRepo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepo repo;
    private final JWTService jwtService;

    public Transaction getTgetImportersTransaction(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        var userId = jwtService.extractUserId(token);

        return repo.findByUserUserId(userId)
                .orElse(null);
    }

    public Transaction getLogisticsTransaction(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        var userId = jwtService.extractUserId(token);

        return repo.findByUserUserId(userId)
                .orElse(null);
    }
}

