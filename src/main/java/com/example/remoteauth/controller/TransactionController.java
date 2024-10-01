package com.example.remoteauth.controller;

import com.example.remoteauth.entity.Transaction;
import com.example.remoteauth.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("importers")
    @PreAuthorize("hasAuthority('IMPORTER')")
    public ResponseEntity<Transaction> getImportersTransaction(HttpServletRequest request) {
        return new ResponseEntity<>(transactionService.getTgetImportersTransaction(request), HttpStatus.OK);
    }

    @GetMapping("logistics")
    @PreAuthorize("hasAuthority('LOGISTICS')")
    public ResponseEntity<Transaction> getLogisticsTransaction(HttpServletRequest request) {
        return new ResponseEntity<>(transactionService.getLogisticsTransaction(request), HttpStatus.OK);
    }
}
