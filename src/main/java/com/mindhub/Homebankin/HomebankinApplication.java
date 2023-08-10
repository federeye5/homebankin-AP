package com.mindhub.Homebankin;

import com.mindhub.Homebankin.models.Account;
import com.mindhub.Homebankin.models.Client;
import com.mindhub.Homebankin.models.Transaction;
import com.mindhub.Homebankin.models.TransactionType;
import com.mindhub.Homebankin.repositories.AccountRepository;
import com.mindhub.Homebankin.repositories.ClientRepository;
import com.mindhub.Homebankin.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class HomebankinApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankinApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData (ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository){
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

		});
	}
}
