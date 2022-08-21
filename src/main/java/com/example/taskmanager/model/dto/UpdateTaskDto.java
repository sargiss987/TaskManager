package com.example.taskmanager.model.dto;

public class UpdateTaskDto {

  private Long id;
  private String userEmail;
  private String taskStatus;

  public UpdateTaskDto() {}

  public UpdateTaskDto(Long id, String userEmail, String taskStatus) {
    this.id = id;
    this.userEmail = userEmail;
    this.taskStatus = taskStatus;
  }

  public static UpdateTaskDto getInstance() {
    return new UpdateTaskDto();
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getTaskStatus() {
    return taskStatus;
  }

  public void setTaskStatus(String taskStatus) {
    this.taskStatus = taskStatus;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
