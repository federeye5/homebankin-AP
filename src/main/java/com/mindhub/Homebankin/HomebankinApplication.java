package com.mindhub.Homebankin;

import com.mindhub.Homebankin.models.*;
import com.mindhub.Homebankin.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class HomebankinApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankinApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData (ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository){
		return (args -> {
			Client client1 = new Client();
			client1.setFirstName("Melba");
			client1.setLastName("Morel");
			client1.setEmail("melba@mindhub.com");

			Client client2 = new Client("Federico","Reyes","federico@mindhub.com");

			clientRepository.save(client1);
			clientRepository.save(client2);

			Account account1 = new Account();
			account1.setNumber("VM0001");
			account1.setCreationDate(LocalDate.now());
			account1.setBalance(5000);
			account1.setDueno(client1);
			clientRepository.save(client1);
			accountRepository.save(account1);

			Account account2 = new Account();
			account2.setNumber("VM0002");
			account2.setCreationDate(LocalDate.now().plusDays(1));
			account2.setBalance(7500);
			account2.setDueno(client1);
			clientRepository.save(client1);
			accountRepository.save(account2);


			Account account3 = new Account();
			account3.setNumber("VM0003");
			account3.setCreationDate(LocalDate.now().plusDays(1));
			account3.setBalance(7500);
			account3.setDueno(client2);
			clientRepository.save(client2);
			accountRepository.save(account3);


			Transaction transaction1 = new Transaction();
			transaction1.setDate(LocalDateTime.now());
			transaction1.setTransaccion(account1);
			transaction1.setAmount(1000);
			transaction1.setType(TransactionType.DEBIT);
			transaction1.setDescripcion("Carne");
			transactionRepository.save(transaction1);
			accountRepository.save(account1);

			Transaction transaction2 = new Transaction();
			transaction2.setDate(LocalDateTime.now());
			transaction2.setTransaccion(account1);
			transaction2.setAmount(1000);
			transaction2.setType(TransactionType.CREDIT);
			transaction2.setDescripcion("Carne");
			transactionRepository.save(transaction2);
			accountRepository.save(account1);

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

			ClientLoan clientLoan1 = new ClientLoan(400000, 60, client1, loan1);
			ClientLoan clientLoan2 = new ClientLoan(50000, 12, client1, loan2);
			ClientLoan clientLoan3 = new ClientLoan(100000, 24, client2, loan2);
			ClientLoan clientLoan4 = new ClientLoan(200000, 36, client2, loan3);
			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan2);
			clientLoanRepository.save(clientLoan3);
			clientLoanRepository.save(clientLoan4);



		});
	}
}
