package com.mindhub.Homebankin.models;

public enum TransactionType {
    CREDIT(1),
    DEBIT(-1);

    private final int sign;

    TransactionType(int sign) {
        this.sign = sign;
    }

    public int getSign() {
        return sign;
    }
}
