package com.infinity.devmarket.controllers;

import com.infinity.devmarket.models.Person;
import com.infinity.devmarket.security.PersonDetails;
import com.infinity.devmarket.services.OrderService;
import com.infinity.devmarket.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final PersonService personService;
    private final OrderService orderService;

    @Autowired
    public ProfileController(PersonService personService, OrderService orderService) {
        this.personService = personService;
        this.orderService = orderService;
    }

    @GetMapping
    public String profilePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        model.addAttribute("person", personDetails.getPerson());
        model.addAttribute("balance", personService.getBalance(personDetails.getWalletAddress()));
        model.addAttribute("orders", orderService.findPersonOrders(personDetails.getPerson().getId()));
        return "profile/profile";
    }

    @GetMapping("/edit")
    public String editProfilePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        model.addAttribute("person", personDetails.getPerson());
        return "profile/edit";
    }

    @PatchMapping("/edit")
    public String editWalletAddress(@ModelAttribute("walletAddress") String walletAddress) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Person updatedPerson = personDetails.getPerson();
        updatedPerson.setWalletAddress(walletAddress);

        personService.update(personDetails.getPerson().getId(), updatedPerson);

        return "redirect:/profile";
    }
}
