package com.soft_telecom.areas.users.controllers;

import com.soft_telecom.areas.users.models.bindingModels.EditUserModel;
import com.soft_telecom.areas.users.models.viewModels.UserViewModel;
import com.soft_telecom.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/all")
    public String getAllUsersPage(Model model){
        List<UserViewModel> users = this.userService.getAllUsers();
        model.addAttribute("users", users);

        return "users/users";
    }

    @GetMapping("edit")
    public String getEditUserPage(Principal principal, Model model){
        if (!model.containsAttribute("editUserModel")){
            EditUserModel editUserModel = this.userService.getUserByUsername(principal.getName());
            model.addAttribute("editUserModel", editUserModel);
        }

        return "users/user-edit";
    }

    @PostMapping("edit")
    public String editUser(@Valid @ModelAttribute EditUserModel editUserModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("editUserModel", editUserModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editUserModel", bindingResult);
            return "redirect:/edit";
        }

        this.userService.editUser(editUserModel);

        return "redirect:/edit";
    }

    @GetMapping("users/delete/{id}")
    public String getDeleteUserPage(@PathVariable long id, Model model){
        EditUserModel editUserModel = this.userService.getUserById(id);
        model.addAttribute("deleteUser", editUserModel);

        return "users/user-delete";
    }

    @PostMapping("users/delete/{id}")
    public String deleteUser(@PathVariable long id){
        this.userService.deleteUser(id);

        return "redirect:/users/all";
    }
}
