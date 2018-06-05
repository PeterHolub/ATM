package com.peterholub.atm.repositories;

import com.peterholub.atm.domains.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {

    boolean existsByCardNumber(Long number);

    Card getCardByCardNumber(Long cardNumber);

}
