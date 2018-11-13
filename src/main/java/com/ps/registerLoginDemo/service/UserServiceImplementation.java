package com.ps.registerLoginDemo.service;

import com.ps.registerLoginDemo.entity.Role;
import com.ps.registerLoginDemo.entity.User;
import com.ps.registerLoginDemo.repository.RolesRepository;
import com.ps.registerLoginDemo.repository.UserRepository;
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

//    private final UserDao userDao;
//    private final RoleDao roleDAO;
//
//    @Autowired
//    public UserServiceImplementation(UserDao userDao, RoleDao roleDAO) {
//        this.userDao = userDao;
//        this.roleDAO = roleDAO;
//    }

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
//            return new org.springframework.security.core.userdetails.User("", "",
//                    getAuthorities(new HashSet<>(Collections.singleton(roleRepository.findRoleByName("ROLE_USER")))));
            throw new UsernameNotFoundException("User not found");
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
