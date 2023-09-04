package com.mindhub.Homebankin.services;

import com.mindhub.Homebankin.dtos.ClientDTO;
import com.mindhub.Homebankin.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    void saveClient(Client client);
    List<ClientDTO> getClientsDTO();

    ClientDTO getClientDTO(Client client);

    Client getClientFindByEmail(String email);

    Optional<Client> getClientFindById(Long id);

    List<Client> getClientsList();
}
