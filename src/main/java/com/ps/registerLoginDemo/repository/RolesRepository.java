package com.ps.registerLoginDemo.repository;

import com.ps.registerLoginDemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
