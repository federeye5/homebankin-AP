package com.mindhub.Homebankin.controllers;


import com.mindhub.Homebankin.dtos.ClientDTO;
import com.mindhub.Homebankin.models.Client;
import com.mindhub.Homebankin.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/clients")
    public List<ClientDTO> getClients(){
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(toList());
    }
    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(

            @RequestParam String firstName, @RequestParam String lastName,

            @RequestParam String email, @RequestParam String password) {


        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {

            String missingField = "";

            if (firstName.isEmpty()) {

                missingField = "First Name";

            } else if (lastName.isEmpty()) {

                missingField = "Last Name";

            } else if (email.isEmpty()) {

                missingField = "Email";

            } else if (password.isEmpty()) {

                missingField = "Password";

            }

            return new ResponseEntity<>(missingField + " is missing", HttpStatus.FORBIDDEN);
        }


        if (clientRepository.findByEmail(email) !=  null) {

            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);

        }

        clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password)));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping("/clients/{id}")
    public ClientDTO clientDTO(@PathVariable Long id){
        return clientRepository.findById(id).map(clientId -> new ClientDTO(clientId)).orElse(null);
    }
    @RequestMapping("/clients/current")
    public ClientDTO getCurrentClient(Authentication authentication) {

        Client client = clientRepository.findByEmail(authentication.getName());

        if (client == null) {
            throw new ResourceNotFoundException("Client not found");
        }

        return new ClientDTO(client);
    }
}
