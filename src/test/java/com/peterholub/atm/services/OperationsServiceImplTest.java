package com.peterholub.atm.services;

import com.peterholub.atm.domains.*;
import com.peterholub.atm.enums.*;
import com.peterholub.atm.repositories.OperationsRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDateTime;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class OperationsServiceImplTest {
    @Mock
    OperationsRepository operationsRepository;
    @InjectMocks
    OperationsServiceImpl operationsService;

    private Card card;

    private LocalDateTime localDateTime;

    private Operations operation;


    @Before
    public void setUp() {
        card = new Card(12345678L, 1111, 4055.01, CardStatus.NORMAL);

        localDateTime = LocalDateTime.now();

        operation = new Operations(card, OperationType.BALANCE, localDateTime, 8099.0, 1500.0);
    }

    @Test
    public void saveOperation() {

        Operations operation = new Operations(card, OperationType.BALANCE, localDateTime, 8099.0, 1500.0);

        operationsService.saveOperation(operation);

        verify(operationsRepository, times(1)).save(operation);

    }

    @Test
    public void findByDateTime() {

        Mockito.when(operationsRepository.findByDateTime(localDateTime)).thenReturn(operation);

        assertEquals(operation, operationsService.findByDateTime(localDateTime));
    }
}