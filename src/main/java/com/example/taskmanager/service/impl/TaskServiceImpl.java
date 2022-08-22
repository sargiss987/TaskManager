package com.example.taskmanager.service.impl;

import com.example.taskmanager.model.dto.CreateTaskDto;
import com.example.taskmanager.model.dto.UpdateStatusDto;
import com.example.taskmanager.model.dto.UpdateTaskDto;
import com.example.taskmanager.model.entity.Task;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.util.mapper.CreateTaskMapper;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;
  private final UserRepository userRepository;

  public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
    this.taskRepository = taskRepository;
    this.userRepository = userRepository;
  }

  @Override
  public List<Task> getAllTasksByCreatedAtDesc() {
    return taskRepository.findAllTasksOrderedByCreatedAtDesc();
  }

  @Override
  public Task createTask(CreateTaskDto createTaskDto) {
    return userRepository
        .findByEmail(createTaskDto.getUserEmail())
        .map(user -> taskRepository.save(CreateTaskMapper.createTaskDtoToTask(createTaskDto, user)))
        .orElseThrow(IllegalArgumentException::new);
  }

  @Override
  public void deleteTask(Long id) {
    taskRepository.deleteById(id);
  }

  @Override
  public Task getTaskById(Long id) {
    return taskRepository.findById(id).orElseThrow(IllegalArgumentException::new);
  }

  @Override
  public UpdateTaskDto getUpdateTaskDtoById(Long id) {
    Task task = getTaskById(id);
    return new UpdateTaskDto(
        task.getId(), task.getUser().getEmail(), task.getTaskStatus().getStatusType());
  }

  @Override
  public UpdateStatusDto getUpdateStatusDtoById(Long id) {
    Task task = getTaskById(id);
    return new UpdateStatusDto(task.getId(), task.getTaskStatus().getStatusType());
  }

  @Override
  public void updateTaskStatus(UpdateStatusDto updateStatusDto) {
    taskRepository.updateTaskStatus(
        updateStatusDto.getId(), updateStatusDto.getTaskStatus(), Instant.now());
  }

  @Override
  public void updateTask(UpdateTaskDto updateTaskDto) {
    taskRepository.updateTask(
        updateTaskDto.getId(),
        updateTaskDto.getTaskStatus(),
        updateTaskDto.getUserEmail(),
        Instant.now());
  }

  @Override
  public List<Task> filterTasksByEmailAndStatus(String email, String status) {
    return taskRepository.filterTasksByEmailAndStatus(email, status);
  }

  @Override
  public List<Task> filterTasksByCreationDateAndStatus(
      String startDate, String endDate, String status, String email) {

    return taskRepository.filterTasksByCreationDateAndStatus(
        LocalDate.parse(startDate), LocalDate.parse(endDate), status, email);
  }

  @Override
  public List<Task> getAllTasksByUserNameAndCreatedAtDesc(String email) {
    return taskRepository.getAllTasksByUserNameAndCreatedAtDesc(email);
  }
}
