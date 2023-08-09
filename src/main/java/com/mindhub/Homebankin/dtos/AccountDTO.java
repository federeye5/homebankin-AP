package com.mindhub.Homebankin.dtos;

import com.mindhub.Homebankin.models.Account;
import com.mindhub.Homebankin.models.Client;

import java.time.LocalDate;

public class AccountDTO {
    private long id;

    private String number;
    private LocalDate creationDate;
    private double balance;

    public AccountDTO(Account account){
        this.number = account.getNumber();
        this.creationDate = account.getCreationDate();
        this.balance = account.getBalance();

    }
    public Long getId(){
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void number(String number) {
        this.number = number;
    }

    public LocalDate getCreationDate() {

        return creationDate;
    }
    public double getBalance(){

        return balance;
    }

}
