package com.example.taskmanager.model.dto;

public class UpdateStatusDto {

  private Long id;
  private String taskStatus;

  public UpdateStatusDto() {}

  public UpdateStatusDto(Long id, String taskStatus) {
    this.id = id;
    this.taskStatus = taskStatus;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTaskStatus() {
    return taskStatus;
  }

  public void setTaskStatus(String taskStatus) {
    this.taskStatus = taskStatus;
  }
}
