package com.mindhub.Homebankin.services;

import com.mindhub.Homebankin.dtos.LoanDTO;
import com.mindhub.Homebankin.models.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanService {

    void saveLoan(Loan loan);

    List<Loan> getListLoans();

    List<LoanDTO> mapToListLoansDTO(List<Loan> loanList);

    Optional<Loan> getOptionalLoanById(Long id);
}
