package com.mindhub.Homebankin.controllers;

import com.mindhub.Homebankin.models.Account;
import com.mindhub.Homebankin.models.Client;
import com.mindhub.Homebankin.models.Transaction;
import com.mindhub.Homebankin.models.TransactionType;
import com.mindhub.Homebankin.repositories.AccountRepository;
import com.mindhub.Homebankin.repositories.ClientRepository;
import com.mindhub.Homebankin.repositories.TransactionRepository;
import com.mindhub.Homebankin.services.AccountService;
import com.mindhub.Homebankin.services.ClientService;
import com.mindhub.Homebankin.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    AccountService accountService;

    @Autowired
    ClientService clientService;

    @Autowired
    TransactionService transactionService;

    @Transactional
    @RequestMapping(path = "/transactions", method = RequestMethod.POST)
    public ResponseEntity<Object> createTransaction(
            @RequestParam String fromAccountNumber,
            @RequestParam String toAccountNumber,
            @RequestParam long amount,
            @RequestParam String description,
            Authentication authentication){

        if (fromAccountNumber.isBlank() || toAccountNumber.isBlank() || amount <= 0 ){

            String missingField = "";

            if (fromAccountNumber.isBlank()) {

                missingField = "From Account Number";

            } else if (toAccountNumber.isBlank()) {

                missingField = "To Account Number";

            } else if (amount <= 0) {

                missingField = "The amount cannot be less than or equal to zero";

            }

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(missingField + " is missing");

        }

        if (fromAccountNumber.equals(toAccountNumber)) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The source account is the same as the destination account");

        }


        Client authenticatedClient = clientService.getClientByEmail(authentication.getName());

        Optional<Account> optionalOriginAccount = accountService.getOptionalAccountByNumber(fromAccountNumber);

        if (!optionalOriginAccount.isPresent() || !optionalOriginAccount.get().getClient().equals(authenticatedClient)){

            if (optionalOriginAccount == null) {

                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This account does not exist");

            }else {

                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not the owner of this account");

            }

        }

        //get the origin account
        Account originAccount = optionalOriginAccount.get();

        //// Verify that the destination account exists
        Optional<Account> optionalDestinationAccount = accountService.getOptionalAccountByNumber(toAccountNumber);

        if (!optionalDestinationAccount.isPresent() ){

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Destination account does not exist");

        }

        //get the destination account
        Account destinationAccount = optionalDestinationAccount.get();

        // Check that the origin account has the amount available
        if (originAccount.getBalance() < amount) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have the funds to complete this transaction");

        }


        Transaction debitTransaction = new Transaction();
        //(description + "(DEBIT" + fromAccountNumber + ")",amount,LocalDateTime.now(),TransactionType.DEBIT); No Me funciona de esta forma ?
        debitTransaction.setDescription("(Debit"+fromAccountNumber+")");
        debitTransaction.setAmount(amount);
        debitTransaction.setType(TransactionType.DEBIT);
        debitTransaction.setDate(LocalDateTime.now());
        Transaction creditTransaction = new Transaction();
        //(TransactionType.CREDIT,amount,description + " (CREDIT " + toAccountNumber + ")", LocalDateTime.now());
        creditTransaction.setDescription("(Credit"+toAccountNumber+")");
        creditTransaction.setAmount(amount);
        creditTransaction.setType(TransactionType.CREDIT);
        creditTransaction.setDate(LocalDateTime.now());

        //link transaction with account
        originAccount.addTransactions(debitTransaction);
        destinationAccount.addTransactions(creditTransaction);

        //Update amounts and save transactions in the repository
        //subtraction from the origin account
        originAccount.setBalance(originAccount.getBalance() - amount);

        //add to the destination account
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);

        transactionService.saveTransaction(debitTransaction);
        transactionService.saveTransaction(creditTransaction);
        accountService.saveAccount(originAccount);
        accountService.saveAccount(destinationAccount);

        return ResponseEntity.status(HttpStatus.CREATED).body("Transaction created successfully.");

    }

}
