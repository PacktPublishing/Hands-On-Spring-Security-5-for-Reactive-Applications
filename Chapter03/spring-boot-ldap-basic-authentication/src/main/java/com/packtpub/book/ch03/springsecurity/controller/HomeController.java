package com.packtpub.book.ch03.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        if(principal != null)
            model.addAttribute("msg", "Welcome " + principal.getName() +" into Spring Boot LDAP managed user using BASIC authentication.");
        return "home";
    }
}
