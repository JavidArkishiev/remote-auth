package com.example.remoteauth.repository;

import com.example.remoteauth.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {
    Optional<Transaction> findByUserUserId(Long userId);

}
