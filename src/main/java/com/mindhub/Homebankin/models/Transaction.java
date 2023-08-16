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
    @JoinColumn(name="transaccion_id")
    private Account transaccion;
    private TransactionType type;
    private LocalDateTime date;
    private double amount;
    private String descripcion;

    public Transaction(){ }

    public Transaction(String descripcion,double amount, LocalDateTime date, Account transaccion, TransactionType type){
        this.descripcion = descripcion;
        this.date = date;
        this.transaccion = transaccion;
        this.type = type;
        this.amount = amount; //* type.getSign();
    }
    public Long getId(){

        return id;
    }
    public String getDescripcion() {

        return descripcion;
    }

    public void setDescripcion(String descripcion) {

        this.descripcion = descripcion;
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
    public Account getTransaccion(){
        return transaccion;
    }

    public void setTransaccion(Account transaccion){
        this.transaccion = transaccion;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }




}
