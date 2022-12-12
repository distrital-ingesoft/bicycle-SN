package com.cyship.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class LoginController {
    @GetMapping({"/","/login"})
    public String index() {
        return "index";
    }
    @GetMapping("/menu")
    public String menu(){
        return "menu";
    }
    @GetMapping("/users")
    public String users(){
        return "user";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}

