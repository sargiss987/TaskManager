package com.example.taskmanager.model.dto;

import com.example.taskmanager.model.entity.User;

public class CreateTaskDto {

  private String taskName;
  private String taskDescription;
  private String userEmail;

  public CreateTaskDto() {}

  public CreateTaskDto(String taskName, String taskDescription, String userEmail) {
    this.taskName = taskName;
    this.taskDescription = taskDescription;
    this.userEmail = userEmail;
  }

  public static CreateTaskDto getInstance() {
    return new CreateTaskDto();
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getTaskDescription() {
    return taskDescription;
  }

  public void setTaskDescription(String taskDescription) {
    this.taskDescription = taskDescription;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }
}
