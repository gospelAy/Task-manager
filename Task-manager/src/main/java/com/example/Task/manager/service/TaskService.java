package com.example.Task.manager.service;

import com.example.Task.manager.dto.TaskRegistrationDto;
import com.example.Task.manager.dto.TaskResponse;

import java.util.List;

public interface TaskService {

    TaskRegistrationDto createTask(TaskRegistrationDto registrationDto);
    List<TaskRegistrationDto> getTaskByCategoryId(Long id);
    TaskRegistrationDto getTaskById(Long taskId, Long categoryId);
    TaskRegistrationDto updateTask(Long categoryId, Long tasksId, TaskRegistrationDto taskRegistrationDto);
    void deleteTask(Long categoryId, Long taskId);
}
