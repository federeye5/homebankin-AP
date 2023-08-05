package com.mindhub.Homebankin;

import com.mindhub.Homebankin.models.Client;
import com.mindhub.Homebankin.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomebankinApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankinApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData (ClientRepository clientRepository){
		return (args -> {
			Client client1 = new Client();
			client1.setFirstName("Melba");
			client1.setLastName("Morel");
			client1.setEmail("melba@mindhub.com");

			Client client2 = new Client("Federico","Reyes","federico@mindhub.com");

			clientRepository.save(client1);
			clientRepository.save(client2);
		});
	}
}
