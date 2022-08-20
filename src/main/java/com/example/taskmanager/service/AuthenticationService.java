package com.example.taskmanager.service;

import com.example.taskmanager.model.dto.UserLoginDto;
import com.example.taskmanager.model.entity.User;

public interface AuthenticationService {

    void authenticateUser(UserLoginDto userLoginDto);

    void addTestUsers();

    User getCurrentUser();
}
