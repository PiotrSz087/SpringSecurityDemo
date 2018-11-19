package com.ps.registerLoginDemo.service;

import com.ps.registerLoginDemo.entity.Role;
import com.ps.registerLoginDemo.entity.User;
import com.ps.registerLoginDemo.repository.RolesRepository;
import com.ps.registerLoginDemo.repository.UserRepository;
import com.ps.registerLoginDemo.validations.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    @Transactional
    public User registerNewUser(User user) throws UserAlreadyExistException {
        if (userRepository.findUserByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("User already exist!");
        }
        User newUserToSave = new User();
        newUserToSave.setUsername(user.getUsername());
        newUserToSave.setPassword(user.getPassword());
        newUserToSave.setRoles(Collections.singleton(rolesRepository.findRoleByName("ROLE_USER")));
        return userRepository.save(newUserToSave);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), 
                user.getPassword(), getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return grantedAuthorities;
    }
}
