package org.example.sqch9ex1.model;

import org.example.sqch9ex1.services.LoggedUserManagementService;
import org.example.sqch9ex1.services.LoginCountService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LoginProcessor {

    private String username;
    private String password;

    private final LoggedUserManagementService loggedUserManagementService;
    private final LoginCountService loginCountService;

    public LoginProcessor(LoggedUserManagementService loggedUserManagementService,
                          LoginCountService loginCountService) {
        this.loggedUserManagementService = loggedUserManagementService;
        this.loginCountService = loginCountService;
    }

    public boolean login() {
        loginCountService.increment();
        String username = this.getUsername();
        String password = this.getPassword();

        boolean loggedIn = "kosya".equals(username) && "pwd".equals(password);
        if (loggedIn) {
            loggedUserManagementService.setUsername(username);
        }
        return loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
