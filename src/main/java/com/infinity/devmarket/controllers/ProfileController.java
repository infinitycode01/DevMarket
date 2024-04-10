package com.infinity.devmarket.controllers;

import com.infinity.devmarket.security.PersonDetails;
import com.infinity.devmarket.services.EncodeService;
import com.infinity.devmarket.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final PersonService personService;
    private final EncodeService encodeService;

    @Autowired
    public ProfileController(PersonService personService, EncodeService encodeService) {
        this.personService = personService;
        this.encodeService = encodeService;
    }

    @GetMapping
    public String profilePage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        model.addAttribute("person", personDetails.getPerson());
        try {
            model.addAttribute("balance", personService.getBalance(encodeService.decode(personDetails.getWalletAddress())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "profile";
    }
}
