package com.peterholub.atm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class OperationsController {

    @GetMapping("/operations")
   public String operationsPage(){

       return "operations";
   }
}
