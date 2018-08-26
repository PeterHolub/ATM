package com.peterholub.atm.services.impl;

import com.peterholub.atm.domains.Card;
import com.peterholub.atm.enums.CardStatus;
import com.peterholub.atm.repositories.CardRepository;
import com.peterholub.atm.services.CardService;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;

import org.junit.runner.RunWith;
import org.mockito.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(JUnitParamsRunner.class)
public class CardServiceImplTest {

    @Mock
    CardRepository cardRepository;
    @InjectMocks
    CardService cardService;

    private long cardNumber;

    private Card card;

    @Before
    public void setUp() {
    //init annotations

        cardService = new CardServiceImpl(cardRepository);
        MockitoAnnotations.initMocks(this);
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
   @Parameters(method = "parametersToTestAdd")
   public void isPinMatch(long cardNumber, int pin, Card card, boolean expectedResult) {

      Mockito.when(cardRepository.getCardByCardNumber(cardNumber)).thenReturn(card);

       assertEquals(expectedResult, cardService.isPinMatch(cardNumber, pin));

    }


    @Test
    public void blockTheCard() {

        //creates fake obj
        Mockito.when(cardRepository.getCardByCardNumber(cardNumber)).thenReturn(card);
        //invoke tested method
        cardService.blockTheCard(cardNumber);

        ArgumentCaptor<Card> argumentCaptor = ArgumentCaptor.forClass(Card.class);
        //catching the modified argument
        verify(cardRepository).save(argumentCaptor.capture());

        assertEquals("BLOCKED", argumentCaptor.getValue().getCardStatus().toString());

        verify(cardRepository, times(1)).save(card);

    }

    @Test
    public void getCard() {

        Mockito.when(cardRepository.getCardByCardNumber(cardNumber)).thenReturn(card);

        assertEquals(card, cardService.getCard(cardNumber));

    }


    @Test

    public void balanceUpdate() {

        Mockito.when(cardRepository.getCardByCardNumber(cardNumber)).thenReturn(card);

        double balanceUpdate = 5000.43;

        cardService.balanceUpdate(cardNumber, balanceUpdate);

        ArgumentCaptor<Card> argumentCaptor = ArgumentCaptor.forClass(Card.class);

        verify(cardRepository).save(argumentCaptor.capture());

        //asserting with delta
        assertEquals(balanceUpdate, argumentCaptor.getValue().getBalance(), .43);

        verify(cardRepository, times(1)).save(card);
    }



    private Object[] parametersToTestAdd() {

        Card cardOne = new Card(1111222233334444L,1111,8080.0,CardStatus.NORMAL);
        Card cardTwo = new Card(5555666677778888L,2222,9000.0,CardStatus.NORMAL);
        Card cardThree = new Card(99999666655558888L,3333,300.0,CardStatus.NORMAL);
        Card cardFour = new Card(2222777755551111L,5555,77777.0,CardStatus.NORMAL);
        return new Object[]{
                new Object[] {cardOne.getCardNumber(), 1111, cardOne, true},
                new Object[] {cardTwo.getCardNumber(), 2222, cardTwo, true},
                new Object[] {cardThree.getCardNumber(), 1234, cardThree, false},
                new Object[] {cardFour.getCardNumber(), 3232, cardFour, false},
        };
    }

}