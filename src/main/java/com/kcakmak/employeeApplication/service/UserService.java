package com.kcakmak.employeeApplication.service;

import com.kcakmak.employeeApplication.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    List<User> findAll();

    User findById(Long userId);

    User save(User theUser);

    void deleteById(Long theUserId);
}
