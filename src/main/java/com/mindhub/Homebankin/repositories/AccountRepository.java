package com.mindhub.Homebankin.repositories;

import com.mindhub.Homebankin.models.Account;
import com.mindhub.Homebankin.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findByClient(Client client);

    Optional<Account> findByNumber(String number);
}
