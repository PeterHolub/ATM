package com.peterholub.atm.services;
import com.peterholub.atm.domains.Operations;
import com.peterholub.atm.repositories.OperationsRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class OperationsServiceImpl implements OperationsService{
   private OperationsRepository operationsRepository;

    public OperationsServiceImpl(OperationsRepository operationsRepository) {
        this.operationsRepository = operationsRepository;
    }

    public void saveOperation(Operations operation) {
        operationsRepository.save(operation);
    }

    @Override
    public Operations findByDateTime(LocalDateTime dateTime) {
        return operationsRepository.findByDateTime(dateTime);
    }


}
