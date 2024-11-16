package com.kcakmak.employeeApplication.dao;

import com.kcakmak.employeeApplication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}