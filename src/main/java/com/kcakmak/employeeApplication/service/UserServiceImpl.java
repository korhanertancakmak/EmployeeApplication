package com.kcakmak.employeeApplication.service;

import com.kcakmak.employeeApplication.dao.RoleRepository;
import com.kcakmak.employeeApplication.dao.UserRepository;
import com.kcakmak.employeeApplication.entity.Role;
import com.kcakmak.employeeApplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository, PasswordEncoder passwordEncoder, RoleRepository theRoleRepository) {
        this.userRepository = theUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = theRoleRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserNameAndEnabledTrue(userName);
    }

    @Override
    public User findById(Long userId) {
        Optional<User> result = userRepository.findById(userId);

        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find user id - " + theUser);
        }

        return theUser;
    }

    @Override
    public User save(User user) {
        // Ensure the user has at least one role
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            // Assuming 'ROLE_EMPLOYEE' is a default role that you want to assign
            Role defaultRole = roleRepository.findByName("ROLE_EMPLOYEE");
            if (defaultRole != null) {
                user.setRoles(Collections.singletonList(defaultRole));
            } else {
                throw new RuntimeException("Default role not found in the database.");
            }
        }


        // Check if the password is already encoded
        if (!user.getPassword().startsWith("$2a$") || user.getPassword().length() != 60) {
            // If not encoded, encode the password and enable the user
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setEnabled(true);

        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserNameAndEnabledTrue(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
