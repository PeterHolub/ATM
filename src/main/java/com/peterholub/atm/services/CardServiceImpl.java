package com.peterholub.atm.services;

import com.peterholub.atm.domains.Card;
import com.peterholub.atm.enums.CardStatus;
import com.peterholub.atm.repositories.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {


    private CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    //    checking if card exist in database
    @Override
    public boolean cardExist(long cardNumber) {
        boolean exist = false;

        if (cardRepository.existsByCardNumber(cardNumber)) {
            exist = true;
        }
        return exist;
    }

    //checking if card status "blocked" or "normal"
    @Override
    public boolean cardStatus(long cardNumber) {

        Card card = cardRepository.getCardByCardNumber(cardNumber);
        boolean status = false;
        if (card.getCardStatus().toString().equals("NORMAL")) {
            status = true;
        }

        return status;
    }

    //checking if pin matches card number
    @Override
    public boolean isPinMatch(long cardNumber, int pinCode) {

        Card card = cardRepository.getCardByCardNumber(cardNumber);

        boolean pinMatch = false;

        if (card.getPin() == pinCode) {

            pinMatch = true;
        }

        return pinMatch;
    }

    //blocking the card by changing card status
    @Override
    public void blockTheCard(long cardNumber) {
        Card card = cardRepository.getCardByCardNumber(cardNumber);
        card.setCardStatus(CardStatus.BLOCKED);
        cardRepository.save(card);

    }

    @Override
    public Card getCard(long cardNumber) {
        return cardRepository.getCardByCardNumber(cardNumber);
    }

    @Override
    public void balanceUpdate(long cardNumber, double balanceUpdate) {
        Card card = cardRepository.getCardByCardNumber(cardNumber);
        card.setBalance(balanceUpdate);
        cardRepository.save(card);
    }

}
