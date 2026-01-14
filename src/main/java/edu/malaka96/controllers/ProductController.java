package edu.malaka96.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/")
    public String home() {
        return "Welcome! Try /session-id for your session.";
    }


    @GetMapping("/session-id")
    public String getSessionId(HttpServletRequest request){
        return "yello user, : "+request.getSession().getId();
    }

}
