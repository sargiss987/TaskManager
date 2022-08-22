package com.example.taskmanager.util.mapper;

import com.example.taskmanager.model.dto.CreateTaskByEmployeeDto;
import com.example.taskmanager.model.dto.CreateTaskByManagerDto;
import com.example.taskmanager.model.entity.Task;
import com.example.taskmanager.model.entity.TaskStatus;
import com.example.taskmanager.model.entity.User;
import java.time.Instant;

public final class CreateTaskMapper {

  private CreateTaskMapper() {}

  public static Task createTaskDtoToTask(CreateTaskByManagerDto createTaskDto, User user) {
    return new Task(
        createTaskDto.getTaskName(),
        createTaskDto.getTaskDescription(),
        Instant.now(),
        TaskStatus.isNewTask(),
        user);
  }

  public static Task createTaskDtoToTask(CreateTaskByEmployeeDto createTaskDto, User user) {
    return new Task(
            createTaskDto.getTaskName(),
            createTaskDto.getTaskDescription(),
            Instant.now(),
            TaskStatus.isNewTask(),
            user);
  }
}
