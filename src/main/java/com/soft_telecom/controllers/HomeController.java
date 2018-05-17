package com.soft_telecom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage(){

        return "home";
    }

    @GetMapping("/about")
    public String getAboutPage(){

        return "about";
    }

    @GetMapping("/contact")
    public String getContactPage(){

        return "contact";
    }
}
