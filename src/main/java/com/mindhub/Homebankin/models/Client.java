package com.mindhub.Homebankin.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToMany(mappedBy = "dueno",fetch = FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>();
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<ClientLoan> loans = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Card> cards = new HashSet<>();
    private String firstName;
    private String lastName;
    private String email;

    public Client() { }

    public Client(String first, String last, String email) {
        this.firstName = first;
        this.lastName = last;
        this.email = email;
    }
    public Long getId(){

        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }
    public String getEmail(){

        return email;
    }
    public void setEmail(String email){

        this.email = email;
    }

    public Set<Account> getAccounts(){
        return accounts;
    }

    public void addAccount(Account account){
        account.setDueno(this);
        accounts.add(account);
    }
    public Set<ClientLoan> getClientLoans() {
        return loans;
    }

    public void addClientLoans(ClientLoan clientLoan) {
        clientLoan.setClient(this);
        loans.add(clientLoan);
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void addCards(Card card) {
        card.setClient(this);
        cards.add(card);
    }

    public String toString() {

        return firstName + " " + lastName+ " "+ email;
    }

}
