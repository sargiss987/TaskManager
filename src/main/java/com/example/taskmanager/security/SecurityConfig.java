package com.example.taskmanager.security;

import com.example.taskmanager.model.enums.RoleType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/api/v1/tasks/manager/**")
        .hasAuthority(RoleType.MANAGER.name())
        .antMatchers("/api/v1/tasks/employee/**")
        .hasAuthority(RoleType.EMPLOYEE.name())
        .antMatchers("*")
        .permitAll()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/")
        .permitAll();
    return http.build();
  }
}
