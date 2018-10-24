package com.ps.registerLoginDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class baseController {

    @GetMapping("/")
    public String showBasePage(){
        return "home";
    }
}
