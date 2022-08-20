package com.example.taskmanager.service;

import com.example.taskmanager.model.dto.CreateTaskDto;
import com.example.taskmanager.model.entity.Task;
import java.util.List;

public interface TaskService {

    List<Task> getAllTasksByCreatedAtDesc();

    Task createTask(CreateTaskDto createTaskDto);

    void deleteTask(Long id);
}
