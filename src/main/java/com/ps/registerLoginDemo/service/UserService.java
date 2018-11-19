package com.ps.registerLoginDemo.service;

import com.ps.registerLoginDemo.entity.User;
import com.ps.registerLoginDemo.validations.UserAlreadyExistException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User registerNewUser(User user) throws UserAlreadyExistException;
}
