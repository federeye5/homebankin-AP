package com.mindhub.Homebankin;

import com.mindhub.Homebankin.models.*;
import com.mindhub.Homebankin.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class RepositoriesTest {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CardRepository cardRepository;

    @Test
    public void existloans(){
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans, is(not(empty())));
    }

    @Test

    public void existPersonalLoan(){
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans, hasItem(hasProperty("name", is("Personal"))));
    }

    @Test
    public void existClients(){
        List<Client> clients = clientRepository.findAll();
        assertThat(clients, is(not(empty())));
    }

    @Test
    public void createClient(){
        Client client = new Client("Aldo","Reyes","aldo@mindhub.com","12345",Role.CLIENT);
        clientRepository.save(client);
        Client clientSaved =clientRepository.findById(client.getId()).orElse(null);

        assertThat(clientSaved, equalTo(client));
    }


    @Test
    public void existAccount(){
        List<Account> accounts = accountRepository.findAll();
        assertThat(accounts, is(not(empty())));
    }

    @Test
    public void createAccount(){
        Account account = new Account("vin-0099", LocalDate.now(), 0.0);
        accountRepository.save(account);

    }

    @Test
    public void existTransaction(){
        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions, is(not(empty())));
    }

    @Test
    public void createTransaction(){
        Transaction transaction = new Transaction("Taxes",20000,LocalDateTime.now(),TransactionType.CREDIT);
        transactionRepository.save(transaction);
        Transaction transactionSaved = transactionRepository.findById(transaction.getId()).orElse(null);

        assertThat(transactionSaved, equalTo(transaction));
    }

    @Test
    public void existCard(){
        List<Card> cards = cardRepository.findAll();
        assertThat(cards, is(not(empty())));
    }

    @Test
    public void createCard(){
        Client client = new Client("Jorge","Ramon","jorge@mindhub.com","123456",Role.CLIENT);
        clientRepository.save(client);
        Card card =  new Card(CardType.DEBIT,CardColor.TITANIUM,"4387 2345 2458 1248",LocalDate.now(),LocalDate.now().plusYears(5),123,client.getFirstName()+" "+client.getLastName());
        cardRepository.save(card);

        Card cardSaved = cardRepository.findById(card.getId()).orElse(null);
        assertThat(cardSaved, equalTo(card));
    }

}
