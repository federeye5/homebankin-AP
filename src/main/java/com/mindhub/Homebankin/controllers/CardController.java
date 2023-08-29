package com.mindhub.Homebankin.controllers;

import com.mindhub.Homebankin.models.Card;
import com.mindhub.Homebankin.models.CardColor;
import com.mindhub.Homebankin.models.CardType;
import com.mindhub.Homebankin.models.Client;
import com.mindhub.Homebankin.repositories.CardRepository;
import com.mindhub.Homebankin.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    ClientRepository clientRepository;

    @RequestMapping(path = "/clients/current/cards",method = RequestMethod.POST)
    public ResponseEntity <Object> createCard(
            @RequestParam CardColor cardColor,
            @RequestParam CardType cardType,
            Authentication authentication){
        if (authentication == null || authentication.getName() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Card color and type are required.");
        }

        Client authenticatedClient = clientRepository.findByEmail(authentication.getName());

        if (authenticatedClient != null) {
            Set<Card> clientCards= authenticatedClient.getCards();
            int cardsOfTypeAndColor = (int) clientCards.stream().filter(card -> card.getType() == cardType && card.getColor() == cardColor).count();
            if (cardsOfTypeAndColor >= 1) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You cannot exceed the limit of 1 cards of this type and color.");
            }
            int cvv;
            String cardNumber;

            do {
                cvv = generateRandomCVV();
                cardNumber = generateRandomCardNumber();
 } while (cardRepository.existsByNumber(cardNumber) || cardRepository.existsByCvv(cvv));

            Card newCard = new Card(cardType,cardColor,cardNumber, LocalDate.now(),LocalDate.now().plusYears(5),cvv,authenticatedClient.getFirstName() + " " + authenticatedClient.getLastName());
            cardRepository.save(newCard);

            authenticatedClient.addCards(newCard);
            clientRepository.save(authenticatedClient);
            return ResponseEntity.status(HttpStatus.CREATED).body("The card was created successfully");
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid authenticated client.");
        }
    }
    private String generateRandomCardNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<4; i++) {
            int n = random.nextInt(9999);
            sb.append(String.format("%04d", n));
            if(i < 3) {
                sb.append("-");
            }
        }
        return sb.toString();
    }
    private int generateRandomCVV() {
        Random random = new Random();
        return random.nextInt(999);
    }
}
