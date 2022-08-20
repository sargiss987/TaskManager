package com.example.taskmanager.controller;

import com.example.taskmanager.service.AuthenticationService;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final AuthenticationService authenticationService;

    public HomeController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login-form");
    }

    // TO:DO
    // must be deleted after registration functionality development
    @PostConstruct
    public void addTestUsers(){
        authenticationService.addTestUsers();
    }
}
