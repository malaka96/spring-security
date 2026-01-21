package edu.malaka96.service;

import edu.malaka96.model.UserPrinciple;
import edu.malaka96.model.Users;
import edu.malaka96.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByName(username);
        if(user == null){
            System.out.println("User is not found");
            throw new UsernameNotFoundException("User is not found");
        }
        return new UserPrinciple(user);
    }
}
