package com.ps.registerLoginDemo.repository;

import com.ps.registerLoginDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    User findUserByUsername(String username);
}
