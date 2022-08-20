package com.example.taskmanager.service.impl;

import com.example.taskmanager.model.dto.UserLoginDto;
import com.example.taskmanager.model.entity.User;
import com.example.taskmanager.model.entity.UserRole;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.security.UserAuthenticationManager;
import com.example.taskmanager.service.AuthenticationService;
import com.example.taskmanager.util.TestUserGenerator;
import java.util.Optional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserAuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(UserAuthenticationManager authenticationManager, BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void authenticateUser(UserLoginDto userLoginDto) {
        SecurityContextHolder.getContext()
                .setAuthentication(
                        authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                        userLoginDto.getEmail(), userLoginDto.getPassword())));
    }

    @Override
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
    }

    // TO:DO
    // must be deleted after registration functionality development
    @Override
    @Transactional
    public void addTestUsers() {
        String managerEmail = "test_manager_1";
        String employeeEmail1 = "test_employee_1";
        String employeeEmail2 = "test_employee_2";
        User testManager = TestUserGenerator.generateTestUser(passwordEncoder,managerEmail, UserRole.isManager());
        User testEmployee1 = TestUserGenerator.generateTestUser(passwordEncoder,employeeEmail1, UserRole.isEmployee());
        User testEmployee2 = TestUserGenerator.generateTestUser(passwordEncoder,employeeEmail2, UserRole.isEmployee());
        Optional<User> manager = userRepository.findByEmail(managerEmail);
        Optional<User> employee1 = userRepository.findByEmail(managerEmail);
        Optional<User> employee2 = userRepository.findByEmail(managerEmail);
        if (isUsersAlreadyExist(manager,employee1,employee2)){
            return;
        }
        userRepository.save(testManager);
        userRepository.save(testEmployee1);
        userRepository.save(testEmployee2);
    }

    private boolean isUsersAlreadyExist(Optional<User> manager, Optional<User> employee1, Optional<User> employee2) {
       return manager.isPresent() || employee1.isPresent() || employee2.isPresent();
    }
}
