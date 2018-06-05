package com.peterholub.atm.repositories;

import com.peterholub.atm.domains.Operations;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface OperationsRepository extends CrudRepository<Operations, Long> {
    @Override
    <S extends Operations> S save(S s);

   Operations findByDateTime(LocalDateTime dateTime);

}
