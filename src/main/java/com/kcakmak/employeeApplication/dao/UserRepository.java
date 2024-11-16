package com.kcakmak.employeeApplication.dao;

import com.kcakmak.employeeApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserNameAndEnabledTrue(String userName);
}
