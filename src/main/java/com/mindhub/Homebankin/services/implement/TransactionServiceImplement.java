package com.mindhub.Homebankin.services.implement;

import com.mindhub.Homebankin.models.Transaction;
import com.mindhub.Homebankin.models.TransactionType;
import com.mindhub.Homebankin.repositories.TransactionRepository;
import com.mindhub.Homebankin.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionServiceImplement implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void saveTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }

    @Override
    public Transaction createTransaction(String description, long amount, LocalDateTime date,TransactionType type){
        return  new Transaction(description,amount,date,type);
    }
}
