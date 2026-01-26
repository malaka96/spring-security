package edu.malaka96.service;

import edu.malaka96.model.Users;
import edu.malaka96.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    final UserRepository userRepository;

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public Users addUser(Users user){
        return userRepository.save(user);
    }

}
