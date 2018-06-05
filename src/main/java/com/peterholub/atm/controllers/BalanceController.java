package com.peterholub.atm.controllers;

import com.peterholub.atm.domains.Card;
import com.peterholub.atm.domains.Operations;
import com.peterholub.atm.enums.OperationType;
import com.peterholub.atm.services.CardServiceImpl;
import com.peterholub.atm.services.OperationsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import java.time.LocalDateTime;

@Controller
public class BalanceController {

    private OperationsServiceImpl operationsService;
    private CardServiceImpl cardService;


    public BalanceController(OperationsServiceImpl operationsService, CardServiceImpl cardService) {
        this.operationsService = operationsService;
        this.cardService = cardService;
    }

    @PostMapping("/balance")
    public String balancePage(@SessionAttribute("cardNumber") long cardNumber, Model model) {
        Card card = cardService.getCard(cardNumber);

        Operations operations = new Operations(card,OperationType.BALANCE,LocalDateTime.now(),card.getBalance(),0.0);
        operationsService.saveOperation(operations);
        model.addAttribute("balance", card.getBalance());
        model.addAttribute("dateTime", operations.getDateTime());

        return "balance";
    }
}
