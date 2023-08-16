package com.mindhub.Homebankin.dtos;

import com.mindhub.Homebankin.models.Transaction;
import com.mindhub.Homebankin.models.TransactionType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionDTO {
    private long id;
    private TransactionType type;
    private String descripcion;
    private LocalDateTime date;
    private double amount;

    public TransactionDTO(Transaction transaction){
        this.id = transaction.getId();
        this.type = transaction.getType();
        this.descripcion = transaction.getDescripcion();
        this.date = transaction.getDate();
        this.amount = transaction.getAmount();
    }
    public Long getId(){

        return id;
    }
    public TransactionType getType(){
        return type;
    }

    public String getDescripcion() {

        return descripcion;
    }

    public void descripcion(String descripcion) {

        this.descripcion = descripcion;
    }

    public LocalDateTime getDate() {

        return date;
    }
    public double getAmount(){

        return amount;
    }
}