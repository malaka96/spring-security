package edu.malaka96.controllers;

import edu.malaka96.model.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public UserModel getUser() {
        return new UserModel("Malaka", "0704132218", "ASE");
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
    public UserModel addUser(@RequestBody UserModel user) {
        return user;
    }

}
