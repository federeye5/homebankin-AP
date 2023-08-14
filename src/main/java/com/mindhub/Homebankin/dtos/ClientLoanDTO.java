package com.mindhub.Homebankin.dtos;

import com.mindhub.Homebankin.models.ClientLoan;

public class ClientLoanDTO {
    private Long id;
    private Long id_clientLoan;
    private Long id_loan;
    private String loanName;
    private double loanAmountRequested;
    private int paymentsRequested;
    private ClientLoan clientLoan;

    public ClientLoanDTO() {}
         public ClientLoanDTO(Long id_clientLoan, Long id_loan, String loanName, double loanAmountRequested, int paymentsRequested,ClientLoan clientLoan /*,Client client, Loan loan*/) {
            this.id_clientLoan = clientLoan.getId();
            this.id_loan = clientLoan.getLoan().getId();
            this.loanName = clientLoan.getLoan().getName();
            this.loanAmountRequested = clientLoan.getAmount();
            this.paymentsRequested = clientLoan.getPayments();
        }
        public ClientLoanDTO(ClientLoan clientLoan) {
        }

        public Long getId() {
            return id;
        }

        public Long getId_clientLoan() {
            return id_clientLoan;
        }

        public Long getId_loan() {
            return id_loan;
        }

        public String getLoanName() {
            return loanName;
        }

        public void setLoanName(String loanName) {
            this.loanName = loanName;
        }

        public double getLoanAmountRequested() {
            return loanAmountRequested;
        }

        public void setLoanAmountRequested(double loanAmountRequested) {
            this.loanAmountRequested = loanAmountRequested;
        }

        public int getPaymentsRequested() {
            return paymentsRequested;
        }

        public void setPaymentsRequested(int paymentsRequested) {
            this.paymentsRequested = paymentsRequested;
        }
        public ClientLoan getClientLoan() {
            return clientLoan;
        }

        public void setClientLoan(ClientLoan clientLoan) {
            this.clientLoan = clientLoan;
        }
    }



