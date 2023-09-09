package com.mindhub.Homebankin.services;

import com.mindhub.Homebankin.models.Client;
import com.mindhub.Homebankin.models.ClientLoan;
import com.mindhub.Homebankin.models.Loan;

import java.util.List;

public interface ClientLoanService {
    void saveClientLoan(ClientLoan clientLoan);

    List<ClientLoan> getClientLoanByEmailAndLoanName(String email, String loanName);

    ClientLoan createClientLoan(Client client, Loan loan, int payments, long amount);
}
