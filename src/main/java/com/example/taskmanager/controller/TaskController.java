package com.example.taskmanager.controller;

import static com.example.taskmanager.constants.Constants.CREATE_TASK_DTO;
import static com.example.taskmanager.constants.Constants.TASKS;
import static com.example.taskmanager.constants.Constants.UPDATE_STATUS_DTO;
import static com.example.taskmanager.constants.Constants.UPDATE_TASK_DTO;
import static com.example.taskmanager.constants.Constants.VALIDATION_ERRORS;

import com.example.taskmanager.model.dto.CreateTaskByEmployeeDto;
import com.example.taskmanager.model.dto.CreateTaskByManagerDto;
import com.example.taskmanager.model.dto.UpdateStatusDto;
import com.example.taskmanager.model.dto.UpdateTaskDto;
import com.example.taskmanager.model.entity.Task;
import com.example.taskmanager.model.validation.ValidationErrors;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.service.ValidationService;
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
  private final ValidationService validationService;

  public TaskController(TaskService taskService, ValidationService validationService) {
    this.taskService = taskService;
    this.validationService = validationService;
  }

  @GetMapping("/manager/filter")
  public ModelAndView filterManagerPageTasks(
      @RequestParam(required = false) String email,
      @RequestParam(required = false) String status,
      Model model) {
    List<Task> tasks = taskService.filterTasksByEmailAndStatus(email, status);
    model.addAttribute(model.addAttribute(TASKS, tasks));
    return new ModelAndView("manager-page");
  }

  @GetMapping("/manager/create")
  public ModelAndView getManagerPageTaskCreationForm(Model model) {
    model.addAttribute(CREATE_TASK_DTO, CreateTaskByManagerDto.getInstance());
    return new ModelAndView("create-task-form");
  }

  @PostMapping("/manager/create")
  public ModelAndView managerPageCreateTask(
      @ModelAttribute CreateTaskByManagerDto createTaskDto, Model model) {
    ValidationErrors validationErrors = validationService.validate(createTaskDto);
    if (validationErrors.hasErrors()){
      model.addAttribute(VALIDATION_ERRORS,validationErrors);
      return new ModelAndView("create-task-form",HttpStatus.BAD_REQUEST);
    }
    taskService.createTask(createTaskDto);
    List<Task> tasks = taskService.getAllTasksByCreatedAtDesc();
    model.addAttribute(model.addAttribute(TASKS, tasks));
    return new ModelAndView("manager-page", HttpStatus.CREATED);
  }

  @GetMapping("/manager/update/{id}")
  public ModelAndView getUpdateTaskPageInManagerPage(@PathVariable Long id, Model model) {
    UpdateTaskDto updateTaskDto = taskService.getUpdateTaskDtoById(id);
    model.addAttribute(UPDATE_TASK_DTO, updateTaskDto);
    return new ModelAndView("update-task-form");
  }

  @PostMapping("manager/update")
  public ModelAndView updateTaskInManagerPage(
      @ModelAttribute UpdateTaskDto updateTaskDto, Model model) {
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

  @GetMapping("/employee/filter")
  public ModelAndView filterEmployeePageTasks(
      @RequestParam(required = false) String startDate,
      @RequestParam(required = false) String endDate,
      @RequestParam(required = false) String status,
      Model model) {
    System.out.println(startDate);
    System.out.println(endDate);
    List<Task> tasks =
        taskService.filterTasksByCreationDateAndStatus(
            startDate, endDate, status, getCurrentUsername());
    model.addAttribute(model.addAttribute(TASKS, tasks));
    return new ModelAndView("employee-page");
  }

  @GetMapping("/employee/create")
  public ModelAndView getEmployeePageTaskCreationForm(Model model) {
    model.addAttribute(CREATE_TASK_DTO, CreateTaskByEmployeeDto.getInstance());
    return new ModelAndView("create-employee-task-form");
  }

  @PostMapping("/employee/create")
  public ModelAndView employeePageCreteTaskCreation(
      @ModelAttribute CreateTaskByEmployeeDto createTaskByEmployeeDto, Model model) {
    ValidationErrors validationErrors = validationService.validate(createTaskByEmployeeDto);
    if (validationErrors.hasErrors()){
      model.addAttribute(VALIDATION_ERRORS,validationErrors);
      return new ModelAndView("create-employee-task-form",HttpStatus.BAD_REQUEST);
    }
    taskService.createTask(createTaskByEmployeeDto,getCurrentUsername());
    List<Task> tasks = taskService.getAllTasksByUserNameAndCreatedAtDesc(getCurrentUsername());
    model.addAttribute(TASKS, tasks);
    return new ModelAndView("employee-page",HttpStatus.CREATED);
  }

  @GetMapping("/employee/update/status/{id}")
  public ModelAndView getUpdateTaskStatusPageInEmployeePage(@PathVariable Long id, Model model) {
    UpdateStatusDto updateTaskDto = taskService.getUpdateStatusDtoById(id);
    model.addAttribute(UPDATE_STATUS_DTO, updateTaskDto);
    return new ModelAndView("update-status-form");
  }

  @PostMapping("/employee/update/status")
  public ModelAndView updateTaskStatusInEmployeePage(
      @ModelAttribute UpdateStatusDto updateStatusDto, Model model) {
    System.out.println(updateStatusDto.getId());
    taskService.updateTaskStatus(updateStatusDto);
    List<Task> tasks = taskService.getAllTasksByUserNameAndCreatedAtDesc(getCurrentUsername());
    model.addAttribute(TASKS, tasks);
    return new ModelAndView("employee-page");
  }

  private String getCurrentUsername() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }
}
