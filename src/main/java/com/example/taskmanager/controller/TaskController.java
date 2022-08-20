package com.example.taskmanager.controller;

import static com.example.taskmanager.constants.Constants.CREATE_TASK_DTO;
import static com.example.taskmanager.constants.Constants.TASKS;

import com.example.taskmanager.model.dto.CreateTaskDto;
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
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
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

  @GetMapping("/update")
  public ModelAndView modifyTask(Model model) {
    // TO: DO
    return new ModelAndView("update-task-form");
  }

  @DeleteMapping("/delete/{id}")
  public ModelAndView deleteTask(@PathVariable Long id,Model model) {
    taskService.deleteTask(id);
    List<Task> tasks = taskService.getAllTasksByCreatedAtDesc();
    model.addAttribute(model.addAttribute(TASKS, tasks));
    return new ModelAndView("manager-page");
  }
}
