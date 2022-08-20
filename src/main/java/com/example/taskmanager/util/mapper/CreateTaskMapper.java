package com.example.taskmanager.util.mapper;

import com.example.taskmanager.model.dto.CreateTaskDto;
import com.example.taskmanager.model.entity.Task;
import com.example.taskmanager.model.entity.TaskStatus;
import com.example.taskmanager.model.entity.User;
import java.time.Instant;

public final class CreateTaskMapper {

  private CreateTaskMapper() {}

  public static Task createTaskDtoToTask(CreateTaskDto createTaskDto, User user) {
    return new Task(
        createTaskDto.getTaskName(),
        createTaskDto.getTaskDescription(),
        Instant.now(),
        TaskStatus.isNewTask(),
        user);
  }
}
