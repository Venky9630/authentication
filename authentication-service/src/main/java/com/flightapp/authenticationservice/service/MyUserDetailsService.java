package com.flightapp.authenticationservice.service;
import com.flightapp.authenticationservice.models.AuthenticationDetails;
import com.flightapp.authenticationservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationDetails user = userRepository.findByEmail(username);
        return new User(username, user.getPwd(), new ArrayList<>());
    }

    public AuthenticationDetails loadUserByEmail(String email) throws UsernameNotFoundException{
       return userRepository.findByEmail(email);
    }

    public AuthenticationDetails saveUser(AuthenticationDetails user){
        return userRepository.save(user);
    }

}
