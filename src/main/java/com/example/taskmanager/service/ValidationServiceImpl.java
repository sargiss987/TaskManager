package com.example.taskmanager.service;

import static com.example.taskmanager.constants.Constants.INCORRECT_USER_NAME;

import com.example.taskmanager.model.dto.CreateTaskByEmployeeDto;
import com.example.taskmanager.model.dto.CreateTaskByManagerDto;
import com.example.taskmanager.model.entity.User;
import com.example.taskmanager.model.validation.ValidationErrors;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.util.validator.Validator;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

  private final UserRepository userRepository;

  public ValidationServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public ValidationErrors validate(CreateTaskByManagerDto createTaskByManagerDto) {
    ValidationErrors validationErrors = Validator.validate(createTaskByManagerDto);
    Optional<User> optionalUser = userRepository.findByEmail(createTaskByManagerDto.getUserEmail());
    if (optionalUser.isEmpty()) {
      validationErrors.setUsernameError(INCORRECT_USER_NAME);
      validationErrors.setHasErrors(true);
    }
    return validationErrors;
  }

  @Override
  public ValidationErrors validate(CreateTaskByEmployeeDto createTaskByEmployeeDto) {
    return Validator.validate(createTaskByEmployeeDto);
  }
}
