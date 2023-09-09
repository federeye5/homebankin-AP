package com.mindhub.Homebankin.services;

import com.mindhub.Homebankin.models.Transaction;
import com.mindhub.Homebankin.models.TransactionType;

import java.time.LocalDateTime;

public interface TransactionService {
    void saveTransaction(Transaction transaction);

    Transaction createTransaction(String description, long amount, LocalDateTime date,TransactionType type);
}
