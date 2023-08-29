package com.mindhub.Homebankin.dtos;



import com.mindhub.Homebankin.models.Card;
import com.mindhub.Homebankin.models.CardColor;
import com.mindhub.Homebankin.models.CardType;

import java.time.LocalDate;

public class CardDTO {
    private long id;
    private CardType type;
    private String number;
    private int cvv;
    private LocalDate fromDate;
    private LocalDate thruDate;
    private String cardHolder;
    private CardColor color;

    public CardDTO(){}
    public CardDTO(Card card) {
        this.id = card.getId();
        this.type = card.getType();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.fromDate = card.getFromDate();
        this.thruDate = card.getThruDate();
        this.cardHolder = card.getCardHolder();
        this.color = card.getColor();
    }

    public long getId() {
        return id;
    }

    public CardType getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public CardColor getColor() {
        return color;
    }
}
