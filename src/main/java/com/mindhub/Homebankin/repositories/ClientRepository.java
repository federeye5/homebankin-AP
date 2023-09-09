package com.mindhub.Homebankin.repositories;

import com.mindhub.Homebankin.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    Client findClientById(Long id);
}
