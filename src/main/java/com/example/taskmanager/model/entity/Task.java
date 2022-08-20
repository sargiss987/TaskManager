package com.example.taskmanager.model.entity;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "task_name", nullable = false)
  private String taskName;

  @Column(name = "task_description", nullable = false)
  private String taskDescription;

  @Column(name = "task_creation_date", nullable = false)
  private Instant createdAt;

  @Column(name = "task_update_date")
  private Instant updatedAt;

  @ManyToOne
  @JoinColumn(name = "task_status_id", nullable = false)
  private TaskStatus taskStatus;

  @ManyToOne
  @JoinColumn(name = "task_manager_user_id")
  private User user;

  public Task() {}

  public Task(
      String taskName,
      String taskDescription,
      Instant createdAt,
      TaskStatus taskStatus,
      User user) {
    this.taskName = taskName;
    this.taskDescription = taskDescription;
    this.createdAt = createdAt;
    this.taskStatus = taskStatus;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public TaskStatus getTaskStatus() {
    return taskStatus;
  }

  public void setTaskStatus(TaskStatus taskStatus) {
    this.taskStatus = taskStatus;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
