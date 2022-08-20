package com.example.taskmanager.service;

import com.example.taskmanager.model.TaskManagerUserDetails;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private static final String USER_NOT_FOUND_EXCEPTION_MESSAGE = "User with %s not found";
  private final UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return new TaskManagerUserDetails(
        userRepository
            .findByEmail(email)
            .orElseThrow(
                () ->
                    new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_EXCEPTION_MESSAGE, email))));
  }
}
