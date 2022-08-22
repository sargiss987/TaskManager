package com.example.taskmanager.service;

import com.example.taskmanager.model.dto.CreateTaskByEmployeeDto;
import com.example.taskmanager.model.dto.CreateTaskByManagerDto;
import com.example.taskmanager.model.validation.ValidationErrors;


public interface ValidationService {

    ValidationErrors validate(CreateTaskByManagerDto createTaskByManagerDto);

    ValidationErrors validate(CreateTaskByEmployeeDto createTaskByEmployeeDto);
}
