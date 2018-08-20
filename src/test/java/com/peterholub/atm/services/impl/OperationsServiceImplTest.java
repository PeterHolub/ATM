package com.peterholub.atm.services.impl;

import com.peterholub.atm.domains.*;
import com.peterholub.atm.enums.*;
import com.peterholub.atm.repositories.OperationsRepository;
import com.peterholub.atm.services.OperationsService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDateTime;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class OperationsServiceImplTest {

    @Spy
    OperationsRepository operationsRepository ;

    private OperationsService operationsService;

    private Card card;

    private LocalDateTime localDateTime;

    private Operations operation;


    @Before
    public void setUp() {

        operationsService = new OperationsServiceImpl(operationsRepository);

        card = new Card(12345678L, 1111, 4055.01, CardStatus.NORMAL);

        localDateTime = LocalDateTime.now();

        operation = new Operations(card, OperationType.BALANCE, localDateTime, 8099.0, 1500.0);
    }

   //TODO Show to Oleg Spy
    @Test
    public void saveOperation() {

        Operations operation = new Operations(card, OperationType.BALANCE, localDateTime, 8099.0, 1500.0);

        operationsService.saveOperation(operation);

        ArgumentCaptor<Operations> argumentCaptor = ArgumentCaptor.forClass(Operations.class);

        verify(operationsRepository).save(argumentCaptor.capture());

        assertEquals(operation.getBalance(), argumentCaptor.getValue().getBalance());

        verify(operationsRepository, times(1)).save(operation);
    }

    @Test
    public void findByDateTime() {
//
        Mockito.when(operationsRepository.findByDateTime(localDateTime)).thenReturn(operation);

        assertEquals(operation, operationsService.findByDateTime(localDateTime));
    }
}