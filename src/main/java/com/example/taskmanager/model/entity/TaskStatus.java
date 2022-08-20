package com.example.taskmanager.model.entity;

import com.example.taskmanager.model.enums.TaskStatusType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task_status")
public class TaskStatus {

  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "status_type", nullable = false)
  private String statusType;

  public TaskStatus() {}

  public TaskStatus(Long id, String status_type) {
    this.id = id;
    this.statusType = status_type;
  }

  public static TaskStatus isNewTask(){
    return new TaskStatus(1L, TaskStatusType.NEW_TASK.name());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStatusType() {
    return statusType;
  }

  public void setStatusType(String statusType) {
    this.statusType = statusType;
  }
}
