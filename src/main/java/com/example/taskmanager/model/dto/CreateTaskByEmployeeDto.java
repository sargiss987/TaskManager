package com.example.taskmanager.model.dto;

public class CreateTaskByEmployeeDto {

  private String taskName;
  private String taskDescription;

  public static CreateTaskByEmployeeDto getInstance() {
    return new CreateTaskByEmployeeDto();
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
}
