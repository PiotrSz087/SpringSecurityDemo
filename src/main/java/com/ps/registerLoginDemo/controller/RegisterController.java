package com.ps.registerLoginDemo.controller;

import com.ps.registerLoginDemo.entity.User;
import com.ps.registerLoginDemo.service.UserService;
import com.ps.registerLoginDemo.validations.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class RegisterController {
    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationPage(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "registration-page";
    }

    @PostMapping("/registerUser")
    public String registerNewUser(@Valid @ModelAttribute("user") User newUser, BindingResult bindingResult, Model model) {
        User userRegistered = new User();
        if (!bindingResult.hasErrors()) {
            try {
                userRegistered = userService.registerNewUser(newUser);
            } catch (UserAlreadyExistException e) {
                userRegistered = null;
            }
        }

        if (userRegistered == null) {
            model.addAttribute("errors", "User already exists.");
            return "registration-page";
        }

        if (bindingResult.hasErrors()) {
            return "registration-page";
        } else {
            model.addAttribute("success", "User registered successfully.");
            return "login-page";
        }
    }
}
