package com.peterholub.atm.controllers;

import com.peterholub.atm.services.impl.CardServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PinEntryController {

    private CardServiceImpl cardService;

    private int attempt = 0;

    public PinEntryController(CardServiceImpl cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/pinEntry")
    public String pinEntryPage(@SessionAttribute("cardNumber") long cardNumber, @RequestParam("keyboardValues") int pinCode, Model model) {

        if (cardService.isPinMatch(cardNumber, pinCode)) {

            return "operations";
        }
        attempt++;

        if (attempt == 4) {
            model.addAttribute("cardIsBlocked", "Your card is blocked now!");
            cardService.blockTheCard(cardNumber);
            return "errorpage";
        }

        model.addAttribute("pinError", "wrong pin code!");

        return "errorpage";
    }

    @GetMapping("/pinEntry")
    public String pinEntry(){

        return"pinentry";
    }

}
