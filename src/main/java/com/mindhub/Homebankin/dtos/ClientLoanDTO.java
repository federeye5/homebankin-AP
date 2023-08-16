package com.mindhub.Homebankin.dtos;

import com.mindhub.Homebankin.models.ClientLoan;

public class ClientLoanDTO {
    private Long id;
    private Long loanId;
    private Long id_loan;
    private String name;
    private double amount;
    private int payments;

    public ClientLoanDTO() {}
         public ClientLoanDTO(ClientLoan clientLoan) {
             this.id = clientLoan.getId();
             this.loanId = clientLoan.getLoan().getId();
             this.name = clientLoan.getLoan().getName();
             this.amount = clientLoan.getAmount();
             this.payments = clientLoan.getPayments();
        }


        public Long getId() {
            return id;
        }

        public Long getLoanId() {
            return loanId;
        }



        public String getName() {
            return name;
        }



        public double getAmount() {
            return amount;
        }



        public int getPayments() {
            return payments;
        }


    }



