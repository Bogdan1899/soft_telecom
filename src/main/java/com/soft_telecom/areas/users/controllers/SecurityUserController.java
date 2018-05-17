package com.soft_telecom.areas.users.controllers;

import com.soft_telecom.configuration.Errors;
import com.soft_telecom.areas.users.models.bindingModels.UserRegistrationModel;
import com.soft_telecom.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SecurityUserController {

    @Autowired
    private UserService userService;

    @GetMapping("register")
    public String getRegistrationPage(@ModelAttribute UserRegistrationModel userRegistrationModel){

        return "users/user-register";
    }

    @PostMapping("register")
    public String registerUser(@Valid @ModelAttribute UserRegistrationModel userRegistrationModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegistrationModel", userRegistrationModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationModel", bindingResult);

            return "redirect:/register";
        }

        this.userService.registerUser(userRegistrationModel);

        return "redirect:/login";
    }

    @GetMapping("login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model){

        if (error != null){
            model.addAttribute("error", Errors.INVALID_CREDENTIALS);
        }

        return "login";
    }
}
