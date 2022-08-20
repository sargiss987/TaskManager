package com.example.taskmanager.repository;

import com.example.taskmanager.model.entity.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

  @Query(value = "SELECT * FROM task ORDER BY task_creation_date DESC;", nativeQuery = true)
  List<Task> findAllTasksOrderedByCreatedAtDesc();
}
