package edu.malaka96.controllers;

import edu.malaka96.model.Users;
import edu.malaka96.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //@ResponseBody + @Controller + @Component
@RequiredArgsConstructor
public class UsersController {

    final UsersService usersService;

    @GetMapping("/users/all")
    public List<Users> getUsers(){
        return usersService.getAllUsers();
    }

    @PostMapping("/users/add")
    public Users addUser(@RequestBody Users user){
        return usersService.addUser(user);
    }

    @PostMapping("/users/login")
    public String login(@RequestBody Users user){
        return usersService.verify(user);
    }
}
