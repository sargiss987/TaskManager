package com.example.taskmanager.repository;

import com.example.taskmanager.model.entity.Task;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

  @Query(value = "SELECT * FROM task ORDER BY task_creation_date DESC;", nativeQuery = true)
  List<Task> findAllTasksOrderedByCreatedAtDesc();

  @Modifying
  @Transactional
  @Query(
      value =
          "UPDATE task t"
              + " INNER JOIN task_status ts on ts.status_type=:status"
              + " INNER JOIN task_manager_user tmu on tmu.email=:username SET"
              + " task_status_id = ifNull(ts.id, task_status_id),"
              + " task_manager_user_id = ifNull(tmu.id, task_manager_user_id),"
              + " task_update_date = :updatedAt"
              + " WHERE t.id=:id",
      nativeQuery = true)
  void updateTask(
      @Param("id") Long id,
      @Param("status") String status,
      @Param("username") String username,
      @Param("updatedAt") Instant updatedAt);

  @Transactional
  @Query(
      value =
          "SELECT * FROM task t"
              + " INNER JOIN task_manager_user tmu on t.task_manager_user_id=tmu.id"
              + " INNER JOIN task_status ts on t.task_status_id=ts.id"
              + " WHERE (LOWER(email) LIKE LOWER(CONCAT('%', :username, '%')))"
              + " AND ts.status_type= if(:status != 'ANY', :status, ts.status_type);",
      nativeQuery = true)
  List<Task> filterTasks(@Param("username") String username, @Param("status") String status);

  @Query(
      value =
          "SELECT * FROM task t"
              + " INNER JOIN task_manager_user tmu on t.task_manager_user_id=tmu.id"
              + " WHERE (tmu.email = :username)"
              + " ORDER BY t.task_creation_date DESC;",
      nativeQuery = true)
  List<Task> getAllTasksByUserNameAndCreatedAtDesc(@Param("username") String username);

  @Modifying
  @Transactional
  @Query(
      value =
          "UPDATE task t"
              + " INNER JOIN task_status ts on ts.status_type=:status SET"
              + " task_update_date = :updatedAt,"
              + " task_status_id = ifNull(ts.id, task_status_id)"
              + " WHERE t.id=:id",
      nativeQuery = true)
  void updateTaskStatus(
      @Param("id") Long id, @Param("status") String status, @Param("updatedAt") Instant updatedAt);
}
