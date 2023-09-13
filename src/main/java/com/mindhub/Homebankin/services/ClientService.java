package com.mindhub.Homebankin.services;

import com.mindhub.Homebankin.dtos.ClientDTO;
import com.mindhub.Homebankin.models.Client;
import com.mindhub.Homebankin.models.Role;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    void saveClient(Client client);
    List<ClientDTO> getClientsDTO();

    ClientDTO getClientDTO(Client client);

    Client getClientByEmail(String email);

    Client getClientById(Long id);

    List<Client> getClientsList();

    Client createClient(String firstName, String lastName, String email, String password, Role role);
}
