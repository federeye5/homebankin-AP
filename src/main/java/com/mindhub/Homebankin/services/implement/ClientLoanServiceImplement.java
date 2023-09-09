package com.mindhub.Homebankin.services.implement;

import com.mindhub.Homebankin.models.Client;
import com.mindhub.Homebankin.models.ClientLoan;
import com.mindhub.Homebankin.models.Loan;
import com.mindhub.Homebankin.repositories.ClientLoanRepository;
import com.mindhub.Homebankin.services.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientLoanServiceImplement implements ClientLoanService {
    @Autowired
    ClientLoanRepository clientLoanRepository;

    @Override
    public void saveClientLoan(ClientLoan clientLoan) {
        clientLoanRepository.save(clientLoan);
    }

    @Override
    public List<ClientLoan> getClientLoanByEmailAndLoanName(String email, String loanName) {
        return clientLoanRepository.findByClientEmailAndLoanName(email,loanName);
    }

    @Override
    public ClientLoan createClientLoan(Client client, Loan loan, int payments, long amount) {
        return new ClientLoan(client,loan,  payments, amount);
    }
}
