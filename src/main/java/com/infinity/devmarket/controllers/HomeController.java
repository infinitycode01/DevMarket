package com.infinity.devmarket.controllers;

import com.infinity.devmarket.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/hi")
    public String sayHi() {
        return "hi";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());*/

        return "hi";
    }
}
