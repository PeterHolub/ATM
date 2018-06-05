package com.peterholub.atm.services;

import com.peterholub.atm.domains.Operations;
import java.time.LocalDateTime;

public interface OperationsService {
    void saveOperation (Operations operation);
    Operations findByDateTime(LocalDateTime dateTime);
}
