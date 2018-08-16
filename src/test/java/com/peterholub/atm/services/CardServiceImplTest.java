package com.peterholub.atm.services;

import com.peterholub.atm.domains.Card;
import com.peterholub.atm.enums.CardStatus;
import com.peterholub.atm.repositories.CardRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CardServiceImplTest {

    @Mock
    CardRepository cardRepository;
    @InjectMocks
    CardServiceImpl cardService;

    private long cardNumber;

    private Card card;

    @Before
    public void setUp()  {
        cardNumber = 12345678L;
        card = new Card(12345678L, 1111, 4055.01, CardStatus.NORMAL);

    }

    @Test
    public void cardExist() {

        Mockito.when(cardRepository.existsByCardNumber(cardNumber)).thenReturn(true);

        assertTrue(cardService.cardExist(cardNumber));
    }

    @Test
    public void cardStatus() {

        Mockito.when(cardRepository.getCardByCardNumber(cardNumber)).thenReturn(card);

        assertTrue(cardService.cardStatus(cardNumber));
    }

    @Test
    public void isPinMatch() {

        Mockito.when(cardRepository.getCardByCardNumber(cardNumber)).thenReturn(card);

        int pin = 1111;
        assertTrue(cardService.isPinMatch(cardNumber, pin));

    }

    //TODO check if this practice normal
    @Test
    public void blockTheCard() {

        Mockito.when(cardRepository.getCardByCardNumber(cardNumber)).thenReturn(card);

        cardService.blockTheCard(cardNumber);

        verify(cardRepository, times(1)).getCardByCardNumber(cardNumber);

        verify(cardRepository, times(1)).save(card);

    }

    @Test
    public void getCard() {

        Mockito.when(cardRepository.getCardByCardNumber(cardNumber)).thenReturn(card);

        assertEquals(card, cardService.getCard(cardNumber));

    }

    //TODO check if this practice normal
    @Test
    public void balanceUpdate() {

        Mockito.when(cardRepository.getCardByCardNumber(cardNumber)).thenReturn(card);

        double balanceUpdate = 5000.43;
        cardService.balanceUpdate(cardNumber, balanceUpdate);

        verify(cardRepository, times(1)).getCardByCardNumber(cardNumber);

        verify(cardRepository, times(1)).save(card);

    }
}