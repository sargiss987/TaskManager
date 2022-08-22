package com.example.taskmanager.controller;

import static com.example.taskmanager.constants.Constants.TASKS;
import static com.example.taskmanager.constants.Constants.USERNAME_NOT_FOUND_EXCEPTION_MESSAGE_KEY;
import static com.example.taskmanager.constants.Constants.USER_LOGIN_DTO;
import static com.example.taskmanager.constants.Constants.USER_PASS_INCORRECT_EXCEPTION_MESSAGE_KEY;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.example.taskmanager.exception.UserPasswordIncorrectException;
import com.example.taskmanager.model.dto.UserLoginDto;
import com.example.taskmanager.model.entity.Task;
import com.example.taskmanager.model.entity.User;
import com.example.taskmanager.model.enums.RoleType;
import com.example.taskmanager.service.AuthenticationService;
import com.example.taskmanager.service.TaskService;
import java.util.List;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final TaskService taskService;

    public AuthenticationController(AuthenticationService authenticationService, TaskService taskService) {
        this.authenticationService = authenticationService;
        this.taskService = taskService;
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute UserLoginDto userLoginDto,Model model){
        authenticationService.authenticateUser(userLoginDto);
        User user = authenticationService.getCurrentUser();
        if (user.getRole().getRoleType().equals(RoleType.MANAGER.name())){
            List<Task> tasks = taskService.getAllTasksByCreatedAtDesc();
            model.addAttribute(TASKS,tasks);
            return new ModelAndView("manager-page");
        }else {
            List<Task> tasks = taskService.getAllTasksByUserNameAndCreatedAtDesc(userLoginDto.getEmail());
            model.addAttribute(TASKS,tasks);
            return new ModelAndView("employee-page");
        }

    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ModelAndView userEmailAlreadyExistException(
            Model model, UsernameNotFoundException exception) {
        model.addAttribute(USER_LOGIN_DTO, UserLoginDto.getInstance());
        model.addAttribute(USERNAME_NOT_FOUND_EXCEPTION_MESSAGE_KEY, exception.getMessage());
        return new ModelAndView("login-form", BAD_REQUEST);
    }

    @ExceptionHandler(value = UserPasswordIncorrectException.class)
    public ModelAndView userPasswordIncorrectException(
            Model model, UserPasswordIncorrectException exception) {
        model.addAttribute(USER_LOGIN_DTO, UserLoginDto.getInstance());
        model.addAttribute(USER_PASS_INCORRECT_EXCEPTION_MESSAGE_KEY, exception.getMessage());
        return new ModelAndView("login-form", BAD_REQUEST);
    }
}
