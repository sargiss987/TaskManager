package com.example.taskmanager.service;

import com.example.taskmanager.model.dto.CreateTaskByEmployeeDto;
import com.example.taskmanager.model.dto.CreateTaskByManagerDto;
import com.example.taskmanager.model.dto.UpdateStatusDto;
import com.example.taskmanager.model.dto.UpdateTaskDto;
import com.example.taskmanager.model.entity.Task;
import java.util.List;

public interface TaskService {

    List<Task> getAllTasksByCreatedAtDesc();

    Task createTask(CreateTaskByManagerDto createTaskDto);

    Task createTask(CreateTaskByEmployeeDto createTaskByEmployeeDto, String currentUsername);

    void deleteTask(Long id);

    Task getTaskById(Long id);

    UpdateTaskDto getUpdateTaskDtoById(Long id);

    void updateTask(UpdateTaskDto updateTaskDto);

    List<Task> filterTasksByEmailAndStatus(String email, String status);

    List<Task> filterTasksByCreationDateAndStatus(String startDate,String endDate, String status,String email);

    List<Task> getAllTasksByUserNameAndCreatedAtDesc(String email);

    UpdateStatusDto getUpdateStatusDtoById(Long id);

    void updateTaskStatus(UpdateStatusDto updateStatusDto);


}
