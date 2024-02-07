package org.example.sqch9ex1.controllers;

import org.example.sqch9ex1.services.LoggedUserManagementService;
import org.example.sqch9ex1.services.LoginCountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class MainController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final LoginCountService loginCountService;

    public MainController(LoggedUserManagementService loggedUserManagementService, LoginCountService loginCountService) {
        this.loggedUserManagementService = loggedUserManagementService;
        this.loginCountService = loginCountService;
    }

    @GetMapping("/main")
    public String mainMethod(@RequestParam(required = false) String logout, Model model){
        if (! Objects.isNull(logout)) {
            loggedUserManagementService.setUsername(null);
        }
        String username = loggedUserManagementService.getUsername();
        if (Objects.isNull(username)) {
            return "redirect:/";
        }
        model.addAttribute("username", username);
        model.addAttribute("loginCount", loginCountService.getCount());
        return "main.html";
    }
}
