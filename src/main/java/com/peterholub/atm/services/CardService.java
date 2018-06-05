package com.peterholub.atm.services;

import com.peterholub.atm.domains.Card;

public interface CardService {

    boolean cardExist(long cardNumber);

    boolean cardStatus(long cardNumber);

    boolean isPinMatch(long cardNumber, int pinCode);

    void blockTheCard(long cardNumber);

    Card getCard(long cardNumber);

    void balanceUpdate(long cardNumber, double balanceUpdate);
}
