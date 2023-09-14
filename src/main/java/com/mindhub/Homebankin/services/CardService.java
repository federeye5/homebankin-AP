package com.mindhub.Homebankin.services;

import com.mindhub.Homebankin.models.Card;
import com.mindhub.Homebankin.models.CardColor;
import com.mindhub.Homebankin.models.CardType;

import java.time.LocalDate;

public interface CardService {
    void saveCard(Card card);

    boolean cardExistsByNumber(String number);

    boolean cardExistsByCvv(int cvv);

    Card getCardById(Long id);

    void deleteCard(Card card);

    Card createCard(CardType type, CardColor color, String number, LocalDate fromDate, LocalDate thruDate, int cvv, String cardHolder);
}
