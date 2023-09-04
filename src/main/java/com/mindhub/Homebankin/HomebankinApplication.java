package com.mindhub.Homebankin;

import com.mindhub.Homebankin.models.*;
import com.mindhub.Homebankin.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class HomebankinApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankinApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Bean
	public CommandLineRunner initData (ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository,
									   LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository){
		return (args -> {
			Client client1 = new Client();
			client1.setFirstName("Melba");
			client1.setLastName("Morel");
			client1.setEmail("melba@mindhub.com");
			client1.setPassword(passwordEncoder.encode("Melba123"));

			Client client2 = new Client("Federico","Reyes","federico@mindhub.com", passwordEncoder.encode("Fede123"));
            Client client3 = new Client("Mario","Flores","admin@mindhub.com", passwordEncoder.encode("admin"));


			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(client3);

			Account account1 = new Account();
			account1.setNumber("VIN-001");
			account1.setCreationDate(LocalDate.now());
			account1.setBalance(5000);
			account1.setClient(client1);
			client1.addAccount(account1);
			clientRepository.save(client1);
			accountRepository.save(account1);

			Account account2 = new Account();
			account2.setNumber("VIN-002");
			account2.setCreationDate(LocalDate.now().plusDays(1));
			account2.setBalance(7500);
			account2.setClient(client1);
			client1.addAccount(account2);
			clientRepository.save(client1);
			accountRepository.save(account2);


			Account account3 = new Account();
			account3.setNumber("VIN-003");
			account3.setCreationDate(LocalDate.now().plusDays(5));
			account3.setBalance(75000);
			account3.setClient(client2);
			client2.addAccount(account3);
			clientRepository.save(client2);
			accountRepository.save(account3);


			Transaction transaction1 = new Transaction();
			transaction1.setDate(LocalDateTime.now());
			transaction1.setAccount(account1);
			transaction1.setAmount(1000);
			transaction1.setType(TransactionType.DEBIT);
			transaction1.setDescription("Carne");
			account1.addTransactions(transaction1);
			transactionRepository.save(transaction1);
			accountRepository.save(account1);

			Transaction transaction2 = new Transaction();
			transaction2.setDate(LocalDateTime.now());
			transaction2.setAccount(account1);
			transaction2.setAmount(1000);
			transaction2.setType(TransactionType.CREDIT);
			transaction2.setDescription("Carne");
			account2.addTransactions(transaction2);
			transactionRepository.save(transaction2);
			accountRepository.save(account1);

			Transaction transaction3 = new Transaction("Pan",1000,LocalDateTime.now(),account3,TransactionType.CREDIT);
			account3.addTransactions(transaction3);
			transactionRepository.save((transaction3));
			accountRepository.save(account3);

			Loan loan1 =new Loan();
			loan1.setName("Mortage");
			loan1.setMaxAmount(500000);
			loan1.setPayments(List.of(12,24,36,48,60));

			Loan loan2 =new Loan("Personal",100000,List.of(6,12,24));

			Loan loan3 =new Loan();
			loan3.setName("Phone");
			loan3.setMaxAmount(300000);
			loan3.setPayments(List.of(6,12,24,36));

			loanRepository.save(loan1);
			loanRepository.save(loan2);
			loanRepository.save(loan3);

			ClientLoan clientLoan1 = new ClientLoan(client1,loan1,60,400000);
			ClientLoan clientLoan2 = new ClientLoan(client1,loan2,12,90000);
			ClientLoan clientLoan3 = new ClientLoan(client2,loan3,36,250000);
			ClientLoan clientLoan4 = new ClientLoan(client2,loan1,12,450000);
			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan2);
			clientLoanRepository.save(clientLoan3);
			clientLoanRepository.save(clientLoan4);

			Card card1 = new Card(CardType.DEBIT,CardColor.GOLD,"6811 5876 2458 1245",LocalDate.now(),LocalDate.now().plusYears(5),123,client1.getFirstName()+" "+client1.getLastName());
			Card card2 = new Card(CardType.CREDIT,CardColor.TITANIUM,"3514 1425 8952 7812",LocalDate.now(),LocalDate.now().plusYears(5),324,client1.getFirstName()+" "+client1.getLastName());
			Card card3 = new Card(CardType.CREDIT,CardColor.SILVER,"4387 2345 2458 1248",LocalDate.now(),LocalDate.now().plusYears(5), 231,client2.getFirstName() + " " + client2.getLastName());

			client1.addCards(card1);
			client1.addCards(card2);
			client2.addCards(card3);
			cardRepository.save(card1);
			cardRepository.save(card2);
			cardRepository.save(card3);
			clientRepository.save(client1);
			clientRepository.save(client1);
			clientRepository.save(client2);




		});
	}
}
