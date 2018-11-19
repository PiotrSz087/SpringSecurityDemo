package com.ps.registerLoginDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    @GetMapping("/basePage")
    public String showBasePage(){
        return "base-page";
    }

    @GetMapping("/forAdmin")
    public String showAdminStuff(){
        return "admin-stuff";
    }

    @GetMapping("/forUsers")
    public String showUserStuff(){
        return "user-stuff";
    }
}
