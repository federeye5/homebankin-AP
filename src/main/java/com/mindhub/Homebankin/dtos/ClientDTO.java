package com.mindhub.Homebankin.dtos;

import com.mindhub.Homebankin.models.Account;
import com.mindhub.Homebankin.models.Client;
import net.minidev.json.annotate.JsonIgnore;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private Long id;
    private Set <AccountDTO> accounts;
    private String firstName;
    private String lastName;
    private String email;

    public ClientDTO(Client client){
        this.id = client.getId();

        this.firstName = client.getFirstName();

        this.lastName = client.getLastName();

        this.email = client.getEmail();

        this.accounts = client.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toSet());

    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }


    public Set<AccountDTO> getAccounts(){
        return accounts;
    }
}
