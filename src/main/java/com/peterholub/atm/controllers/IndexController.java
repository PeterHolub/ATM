package com.peterholub.atm.controllers;

import com.peterholub.atm.services.impl.CardServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;


@Controller

public class IndexController {


    private CardServiceImpl cardService;

    public IndexController(CardServiceImpl cardService) {
        this.cardService = cardService;
    }

    @RequestMapping("/index")

    public String indexPage(@RequestParam("keyboardValues") String number, Model model, HttpSession session) {

        //Getting values from home page and replace all"-" symbols

        long cardNumber = Long.parseLong(number.replaceAll("[-]", ""));

        //Checking if card exist
        if (!cardService.cardExist(cardNumber)) {

            model.addAttribute("wrongCardNumber", "Wrong card Number!");
            return "errorpage";
        }
        //Checking if card is blocked
        if (!cardService.cardStatus(cardNumber)) {
            model.addAttribute("cardBlocked", "Your card is blocked!");
            return "errorpage";

        }
        //writing cardNumber to session
        session.setAttribute("cardNumber", cardNumber);
        return "pinentry";
    }

}
