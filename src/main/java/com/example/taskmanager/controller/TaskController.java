package com.example.taskmanager.controller;

import static com.example.taskmanager.constants.Constants.CREATE_TASK_DTO;
import static com.example.taskmanager.constants.Constants.TASKS;
import static com.example.taskmanager.constants.Constants.UPDATE_STATUS_DTO;
import static com.example.taskmanager.constants.Constants.UPDATE_TASK_DTO;

import com.example.taskmanager.model.dto.CreateTaskDto;
import com.example.taskmanager.model.dto.UpdateStatusDto;
import com.example.taskmanager.model.dto.UpdateTaskDto;
import com.example.taskmanager.model.entity.Task;
import com.example.taskmanager.service.TaskService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping("/manager/filter")
  public ModelAndView filterTasks(
      @RequestParam(required = false) String email,
      @RequestParam(required = false) String status,
      Model model) {
    List<Task> tasks = taskService.filterTasks(email, status);
    model.addAttribute(model.addAttribute(TASKS, tasks));
    return new ModelAndView("manager-page");
  }

  @GetMapping("/manager/create")
  public ModelAndView getTaskCreationForm(Model model) {
    model.addAttribute(CREATE_TASK_DTO, CreateTaskDto.getInstance());
    return new ModelAndView("create-task-form");
  }

  @PostMapping("/manager/create")
  public ModelAndView createTask(@ModelAttribute CreateTaskDto createTaskDto, Model model) {
    taskService.createTask(createTaskDto);
    List<Task> tasks = taskService.getAllTasksByCreatedAtDesc();
    model.addAttribute(model.addAttribute(TASKS, tasks));
    return new ModelAndView("manager-page", HttpStatus.CREATED);
  }

  @GetMapping("/manager/update/{id}")
  public ModelAndView getUpdateTaskPage(@PathVariable Long id, Model model) {
    UpdateTaskDto updateTaskDto = taskService.getUpdateTaskDtoById(id);
    model.addAttribute(UPDATE_TASK_DTO, updateTaskDto);
    return new ModelAndView("update-task-form");
  }

  @PostMapping("manager/update")
  public ModelAndView updateTask(@ModelAttribute UpdateTaskDto updateTaskDto, Model model) {
    taskService.updateTask(updateTaskDto);
    List<Task> tasks = taskService.getAllTasksByCreatedAtDesc();
    model.addAttribute(model.addAttribute(TASKS, tasks));
    model.addAttribute(UPDATE_TASK_DTO, updateTaskDto);
    return new ModelAndView("manager-page");
  }

  @DeleteMapping("/manager/delete/{id}")
  public ModelAndView deleteTask(@PathVariable Long id, Model model) {
    taskService.deleteTask(id);
    List<Task> tasks = taskService.getAllTasksByCreatedAtDesc();
    model.addAttribute(model.addAttribute(TASKS, tasks));
    return new ModelAndView("manager-page");
  }

  @GetMapping("/employee/update/status/{id}")
  public ModelAndView getUpdateTaskStatusPage(@PathVariable Long id, Model model) {
    System.out.println(id);
    UpdateStatusDto updateTaskDto = taskService.getUpdateStatusDtoById(id);
    model.addAttribute(UPDATE_STATUS_DTO, updateTaskDto);
    return new ModelAndView("update-status-form");
  }

  @PostMapping("/employee/update/status")
  public ModelAndView updateTaskStatus(
      @ModelAttribute UpdateStatusDto updateStatusDto, Model model) {
    System.out.println(updateStatusDto.getId());
    taskService.updateTaskStatus(updateStatusDto);
    List<Task> tasks = taskService.getAllTasksByUserNameAndCreatedAtDesc(getCurrentUsername());
    model.addAttribute(TASKS,tasks);
    return new ModelAndView("employee-page");
  }

  private String getCurrentUsername(){
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }
}
