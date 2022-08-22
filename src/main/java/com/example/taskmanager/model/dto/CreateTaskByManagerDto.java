package com.example.taskmanager.model.dto;

public class CreateTaskByManagerDto {

  private String taskName;
  private String taskDescription;
  private String userEmail;

  public CreateTaskByManagerDto() {}

  public CreateTaskByManagerDto(String taskName, String taskDescription, String userEmail) {
    this.taskName = taskName;
    this.taskDescription = taskDescription;
    this.userEmail = userEmail;
  }

  public static CreateTaskByManagerDto getInstance() {
    return new CreateTaskByManagerDto();
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
