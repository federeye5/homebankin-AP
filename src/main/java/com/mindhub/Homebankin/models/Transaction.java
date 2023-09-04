package com.mindhub.Homebankin.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="account_id")
    private Account account;
    private TransactionType type;
    private LocalDateTime date;
    private double amount;
    private String description;

    public Transaction(){ }

    public Transaction(String description,double amount, LocalDateTime date, TransactionType type){
        this.description = description;
        this.date = date;

        this.type = type;
        this.amount = amount; //* type.getSign();
    }
    public Long getId(){

        return id;
    }
    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }
    public LocalDateTime getDate() {

        return date;
    }

    public void setDate(LocalDateTime date) {

        this.date = date;
    }

    public double getAmount(){

        return amount;
    }

    public void setAmount(double amount){

        this.amount = amount;
    }
    public Account getAccount(){
        return account;
    }

    public void setAccount(Account account){
        this.account = account;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }




}
