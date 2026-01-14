package edu.malaka96.controllers;

import edu.malaka96.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public User getUser() {
        return new User("Malaka", "0704132218", "ASE");
    }

    @GetMapping("/session-id")
    public String getSessionId(HttpServletRequest request) {
        return "yello user, : " + request.getSession().getId();
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }

    @PostMapping("/add-user")
    public User addUser(@RequestBody User user) {
        return user;
    }

}
