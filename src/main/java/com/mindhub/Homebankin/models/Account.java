package com.mindhub.Homebankin.models;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dueno_id")
    private Client dueno;
    private String number;
    private LocalDate creationDate;
    private double balance;




    public Account() { }

    public Account(String number,LocalDate creationDate, double balance,Client dueno){
        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
        this.dueno = dueno;
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

    public void setCreationDate(LocalDate creationDate) {

        this.creationDate = creationDate;
    }
    public double getBalance(){

        return balance;
    }
    public void setBalance(double balance){

        this.balance = balance;
    }


    public Client getDueno(){
        return dueno;
    }

    public void setDueno(Client dueno){
        this.dueno = dueno;
    }
}
