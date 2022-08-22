package com.example.taskmanager.util.validator;

import static com.example.taskmanager.constants.Constants.FIELD_CANT_BE_EMPTY;

import com.example.taskmanager.model.dto.CreateTaskByEmployeeDto;
import com.example.taskmanager.model.dto.CreateTaskByManagerDto;
import com.example.taskmanager.model.validation.ValidationErrors;

public class Validator {
  public static ValidationErrors validate(CreateTaskByManagerDto createTaskByManagerDto) {
    ValidationErrors validationErrors = new ValidationErrors();
    if (createTaskByManagerDto.getTaskName() == null
        || createTaskByManagerDto.getTaskName().trim().isEmpty()) {
      validationErrors.setTaskNameError(FIELD_CANT_BE_EMPTY);
      validationErrors.setHasErrors(true);
    }
    if (createTaskByManagerDto.getTaskDescription() == null
        || createTaskByManagerDto.getTaskDescription().trim().isEmpty()) {
      validationErrors.setTaskDescriptionError(FIELD_CANT_BE_EMPTY);
      validationErrors.setHasErrors(true);
    }
    return validationErrors;
  }

  public static ValidationErrors validate(CreateTaskByEmployeeDto createTaskByEmployeeDto) {
    ValidationErrors validationErrors = new ValidationErrors();
    if (createTaskByEmployeeDto.getTaskName() == null
            || createTaskByEmployeeDto.getTaskName().trim().isEmpty()) {
      validationErrors.setTaskNameError(FIELD_CANT_BE_EMPTY);
      validationErrors.setHasErrors(true);
    }
    if (createTaskByEmployeeDto.getTaskDescription() == null
            || createTaskByEmployeeDto.getTaskDescription().trim().isEmpty()) {
      validationErrors.setTaskDescriptionError(FIELD_CANT_BE_EMPTY);
      validationErrors.setHasErrors(true);
    }
    return validationErrors;
  }


}
