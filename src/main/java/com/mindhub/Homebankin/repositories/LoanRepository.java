package com.mindhub.Homebankin.repositories;

import com.mindhub.Homebankin.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan,Long> {
}
