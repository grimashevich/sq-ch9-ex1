package org.example.sqch9ex1.controllers;

import org.example.sqch9ex1.model.LoginProcessor;
import org.example.sqch9ex1.services.LoginCountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final LoginProcessor loginProcessor;
    private final LoginCountService loginCountService;

    public LoginController(LoginProcessor loginProcessor, LoginCountService loginCountService) {
        this.loginProcessor = loginProcessor;
        this.loginCountService = loginCountService;
    }

    @GetMapping("/")
    public String loginGet() {
        return "login.html";
    }

    @PostMapping("/")
    public String processLogin(@RequestParam String username, @RequestParam String password, Model model) {
        System.out.println(loginProcessor);
        loginProcessor.setUsername(username);
        loginProcessor.setPassword(password);
        boolean loggedIn = loginProcessor.login();

        if (loggedIn) {
            return "redirect:/main";
        } else {
            model.addAttribute("message", "Login failed");
            model.addAttribute("loginCount", loginCountService.getCount());
        }
        return "login.html";
    }
}
