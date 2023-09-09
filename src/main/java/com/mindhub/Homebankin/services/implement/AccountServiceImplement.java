package com.mindhub.Homebankin.services.implement;

import com.mindhub.Homebankin.dtos.AccountDTO;
import com.mindhub.Homebankin.models.Account;
import com.mindhub.Homebankin.models.Client;
import com.mindhub.Homebankin.repositories.AccountRepository;
import com.mindhub.Homebankin.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class AccountServiceImplement implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Optional<Account> getOptionalAccountByNumber (String number){
        return  accountRepository.findByNumber(number);
    }

    @Override
    public void saveAccount (Account account){
        accountRepository.save(account);
    }
    @Override
    public List<AccountDTO> getListAccountDTO() {
        return accountRepository.findAll().stream().map(this::getAccountDTO).collect(toList());
    }

    @Override
    public Optional<Account> getOptionalAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public List<Account> getAccountsByClient(Client client) {
        return accountRepository.findByClient(client);
    }

    @Override
    public List<AccountDTO> mapToAccountDTOList(List<Account> accounts) {
        return accounts.stream().map(this::getAccountDTO).collect(Collectors.toList());
    }

    @Override
    public AccountDTO getAccountDTO(Account account) {
        return new AccountDTO(account);
    }

    @Override
    public Account createAccount(String number, LocalDate creationDate, double balance) {
        return new Account(number,creationDate,balance);
    }

}
