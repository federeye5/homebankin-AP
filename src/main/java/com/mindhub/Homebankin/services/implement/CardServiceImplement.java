package com.mindhub.Homebankin.services.implement;

import com.mindhub.Homebankin.models.Card;
import com.mindhub.Homebankin.models.CardColor;
import com.mindhub.Homebankin.models.CardType;
import com.mindhub.Homebankin.repositories.CardRepository;
import com.mindhub.Homebankin.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CardServiceImplement implements CardService {
    @Autowired
    private CardRepository cardRepository;

    @Override
    public void saveCard(Card card) {
        cardRepository.save(card);
    }

    @Override
    public boolean cardExistsByNumber(String number) {
        return cardRepository.existsByNumber(number);
    }

    @Override
    public boolean cardExistsByCvv(int cvv) {
        return cardRepository.existsByCvv(cvv);
    }

    @Override
    public Card createCard(CardType type, CardColor color, String number, LocalDate fromDate, LocalDate thruDate, int cvv, String cardHolder) {
        return new Card(type,color,number,fromDate,thruDate,cvv,cardHolder);
    }
}
