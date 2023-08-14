package com.mindhub.Homebankin.repositories;

import com.mindhub.Homebankin.models.ClientLoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientLoanRepository extends JpaRepository<ClientLoan,Long> {
}
