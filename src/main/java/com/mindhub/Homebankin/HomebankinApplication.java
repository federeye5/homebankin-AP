package com.mindhub.Homebankin;

import com.mindhub.Homebankin.models.Account;
import com.mindhub.Homebankin.models.Client;
import com.mindhub.Homebankin.repositories.AccountRepository;
import com.mindhub.Homebankin.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class HomebankinApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankinApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData (ClientRepository clientRepository, AccountRepository accountRepository){
		return (args -> {
			Client client1 = new Client();
			client1.setFirstName("Melba");
			client1.setLastName("Morel");
			client1.setEmail("melba@mindhub.com");

			Client client2 = new Client("Federico","Reyes","federico@mindhub.com");

			clientRepository.save(client1);
			clientRepository.save(client2);

			Account account1 = new Account();
			account1.setCreationDate(LocalDate.now());
			account1.setBalance(5000);
			account1.setDueno(client1);
			clientRepository.save(client1);
			accountRepository.save(account1);

			Account account2 = new Account();
			account2.setCreationDate(LocalDate.now().plusDays(1));
			account2.setBalance(7500);
			account2.setDueno(client1);
			accountRepository.save(account2);
			clientRepository.save(client1);
		});
	}
}
