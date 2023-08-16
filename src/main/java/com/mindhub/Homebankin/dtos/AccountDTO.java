package com.mindhub.Homebankin.dtos;

import com.mindhub.Homebankin.models.Account;
import com.mindhub.Homebankin.models.Client;
import com.mindhub.Homebankin.models.TransactionType;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {
    private long id;
    private Set<TransactionDTO> transactions;

    private String number;
    private LocalDate creationDate;
    private double balance;

    public AccountDTO(){}
    public AccountDTO(Account account){
        this.id = account.getId();
        this.number = account.getNumber();
        this.creationDate = account.getCreationDate();
        this.balance = account.getBalance();
        this.transactions = account.getTransactions().stream().map(TransactionDTO::new).collect(Collectors.toSet());

    }
    public Long getId(){
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getCreationDate() {

        return creationDate;
    }
    public double getBalance(){

        return balance;
    }
    public Set<TransactionDTO> getTransactions(){

        return transactions;

    }

}
