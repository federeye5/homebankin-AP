package com.mindhub.Homebankin.services.implement;

import com.mindhub.Homebankin.dtos.LoanDTO;
import com.mindhub.Homebankin.models.Loan;
import com.mindhub.Homebankin.repositories.LoanRepository;
import com.mindhub.Homebankin.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanServiceImplement implements LoanService {
    @Autowired
    LoanRepository loanRepository;

    @Override
    public void saveLoan(Loan loan) {
        loanRepository.save(loan);
    }

    @Override
    public List<Loan> getListLoans() {
        return loanRepository.findAll();
    }

    @Override
    public List<LoanDTO> mapToListLoansDTO(List<Loan> loanList) {
        return loanList.stream().map(LoanDTO::new).collect(Collectors.toList());
    }

    @Override
    public Optional<Loan> getOptionalLoanById(Long id) {
        return loanRepository.findById(id);
    }
}