package com.packtpub.book.ch07.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        if(principal != null)
            model.addAttribute("msg", "Welcome " + principal.getName() +" into Spring Boot in-memory managed user using BASIC authentication with CORS enabled.");
        return "home";
    }
}
