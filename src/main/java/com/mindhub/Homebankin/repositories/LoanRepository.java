package com.mindhub.Homebankin.repositories;

import com.mindhub.Homebankin.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface LoanRepository extends JpaRepository<Loan,Long> {
    Optional<Loan> findById(Long id);
}
