package com.mindhub.Homebankin.controllers;

import com.mindhub.Homebankin.dtos.LoanApplicationDTO;
import com.mindhub.Homebankin.dtos.LoanDTO;
import com.mindhub.Homebankin.models.*;
import com.mindhub.Homebankin.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientLoanRepository clientLoanRepository;

    @Autowired
    TransactionRepository transactionRepository;


    @RequestMapping(path="/loans")
    public ResponseEntity<Object>getLoans(Authentication authentication){


        if (authentication == null || !authentication.isAuthenticated()) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication required.");

        }

        List<Loan> loans = loanRepository.findAll();


        List<LoanDTO> loanDTOs = loans.stream()
                .map(LoanDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(loanDTOs);

    }

    @Transactional
    @RequestMapping(path="/loans", method= RequestMethod.POST)
    public ResponseEntity<Object> createLoan(
            @RequestBody LoanApplicationDTO loanApplicationDTO,
            Authentication authentication) {


        if (authentication == null || !authentication.isAuthenticated()) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication required.");

        }
        if (loanApplicationDTO.getAmount() <= 0 || loanApplicationDTO.getPayments() <= 0) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    "Invalid data: the fields cannot be empty and the amount " +
                            "and fees cannot be less than or equal to zero");

        }

        Optional<Loan> optionalLoan = loanRepository.findById(loanApplicationDTO.getLoanId());

        if (optionalLoan.isEmpty()) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The loan does not exist");

        }


        Loan loan = optionalLoan.get();

        if (loanApplicationDTO.getAmount()> loan.getMaxAmount()) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Loan amount exceeds maximum.");

        }

        if (!loan.getPayments().contains(loanApplicationDTO.getPayments())) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid number of payments");

        }

        Optional<Account> optionalAccount = accountRepository.findByNumber(loanApplicationDTO.getToAccountNumber());

        if (optionalAccount.isEmpty()) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Destination account does not exist");

        }

        List <ClientLoan> existingLoans = clientLoanRepository.findByClientEmailAndLoanName(authentication.getName(), loan.getName());

        if (!existingLoans.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You already have a loan with this name.");
        }

        Account destinationAccount = optionalAccount.get();

        Client authenticadedClient = clientRepository.findByEmail(authentication.getName());

        List<Account> authenticatedClientAccounts = accountRepository.findByClient(authenticadedClient);

        if (!authenticatedClientAccounts.contains(destinationAccount)) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This destination account does not belong to the logged in client");

        }

        long totalAmount = (long) (loanApplicationDTO.getAmount() * 1.20);

        ClientLoan loanRequest = new ClientLoan(authenticadedClient,loan,loanApplicationDTO.getPayments(),totalAmount);

        authenticadedClient.addClientLoan(loanRequest);


        Transaction creditTransaction = new Transaction(loan.getName()+"Loan Aproved",totalAmount,LocalDateTime.now(),TransactionType.CREDIT);

        destinationAccount.setBalance(destinationAccount.getBalance() + loanRequest.getAmount());

        //save in repositories
        clientLoanRepository.save(loanRequest);
        transactionRepository.save(creditTransaction);
        accountRepository.save(destinationAccount);
        clientRepository.save(authenticadedClient);


        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully requested loan");
    }


}