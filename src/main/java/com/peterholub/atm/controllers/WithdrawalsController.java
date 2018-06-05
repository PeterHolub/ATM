package com.peterholub.atm.controllers;

import com.peterholub.atm.domains.*;
import com.peterholub.atm.enums.OperationType;
import com.peterholub.atm.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
public class WithdrawalsController {

    private CardServiceImpl cardService;
    private OperationsServiceImpl operationsService;

    public WithdrawalsController(CardServiceImpl cardService, OperationsServiceImpl operationsService) {
        this.cardService = cardService;
        this.operationsService = operationsService;
    }


    @PostMapping("/withdrawals")
    public String withdrawalsPage(@SessionAttribute("cardNumber") long cardNumber, @RequestParam("keyboardValues") double amountWithdraw, Model model) {
        Card card = cardService.getCard(cardNumber);

        if (amountWithdraw > card.getBalance()) {

            model.addAttribute("amountExceed", "Entered amount exceed your balance");
            return "errorpage";
        }

        //withdrawn operation
        double balanceUpdate = card.getBalance() - amountWithdraw;


        //saving updating card balance
        cardService.balanceUpdate(cardNumber, balanceUpdate);

        //writing operation to database

        Operations operation = new Operations(card,OperationType.WITHDRAWAL,LocalDateTime.now(),balanceUpdate,amountWithdraw);

        operationsService.saveOperation(operation);

        model.addAttribute("Operation", operationsService.findByDateTime(operation.getDateTime()));
        return "operationsreporst";
    }


    @GetMapping("/withdrawals")
    public String getWithdrawals() {
        return "withdrawals";
    }
}
