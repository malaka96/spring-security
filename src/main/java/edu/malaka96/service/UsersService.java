package edu.malaka96.service;

import edu.malaka96.model.Users;
import edu.malaka96.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    final UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public Users addUser(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
