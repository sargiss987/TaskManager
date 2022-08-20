package com.example.taskmanager.util;

import com.example.taskmanager.model.entity.User;
import com.example.taskmanager.model.entity.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class TestUserGenerator {

  private TestUserGenerator() {}

  public static User generateTestUser(
      BCryptPasswordEncoder passwordEncoder, String email, UserRole role) {
    return new User(
        "TestManager", "TestManager", passwordEncoder.encode("TestPassword"), email, role);
  }
}
