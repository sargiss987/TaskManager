package com.example.taskmanager.controller;

import static com.example.taskmanager.constants.Constants.CREATE_TASK_DTO;
import static com.example.taskmanager.constants.Constants.TASKS;
import static com.example.taskmanager.constants.Constants.UPDATE_TASK_DTO;

import com.example.taskmanager.model.dto.CreateTaskDto;
import com.example.taskmanager.model.dto.UpdateTaskDto;
import com.example.taskmanager.model.entity.Task;
import com.example.taskmanager.service.TaskService;
import java.util.List;
import org.springframework.http.HttpStatus;
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

  @GetMapping("/filter")
  public ModelAndView filterTasks(
      @RequestParam(required = false) String email,
      @RequestParam(required = false) String status,
      Model model) {
    List<Task> tasks = taskService.filterTasks(email, status);
    model.addAttribute(model.addAttribute(TASKS, tasks));
    return new ModelAndView("manager-page");
  }

  @GetMapping("/create")
  public ModelAndView getTaskCreationForm(Model model) {
    model.addAttribute(CREATE_TASK_DTO, CreateTaskDto.getInstance());
    return new ModelAndView("create-task-form");
  }

  @PostMapping("/create")
  public ModelAndView createTask(@ModelAttribute CreateTaskDto createTaskDto, Model model) {
    taskService.createTask(createTaskDto);
    List<Task> tasks = taskService.getAllTasksByCreatedAtDesc();
    model.addAttribute(model.addAttribute(TASKS, tasks));
    return new ModelAndView("manager-page", HttpStatus.CREATED);
  }

  @GetMapping("/update/{id}")
  public ModelAndView getUpdateTaskPage(@PathVariable Long id, Model model) {
    UpdateTaskDto updateTaskDto = taskService.getUpdateTaskDtoById(id);
    model.addAttribute(UPDATE_TASK_DTO, updateTaskDto);
    return new ModelAndView("update-task-form");
  }

  @PostMapping("/update/{id}")
  public ModelAndView updateTask(@ModelAttribute UpdateTaskDto updateTaskDto, Model model) {
    taskService.updateTask(updateTaskDto);
    List<Task> tasks = taskService.getAllTasksByCreatedAtDesc();
    model.addAttribute(model.addAttribute(TASKS, tasks));
    model.addAttribute(UPDATE_TASK_DTO, updateTaskDto);
    return new ModelAndView("manager-page");
  }

  @DeleteMapping("/delete/{id}")
  public ModelAndView deleteTask(@PathVariable Long id, Model model) {
    taskService.deleteTask(id);
    List<Task> tasks = taskService.getAllTasksByCreatedAtDesc();
    model.addAttribute(model.addAttribute(TASKS, tasks));
    return new ModelAndView("manager-page");
  }
}
