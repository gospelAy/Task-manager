package com.example.Task.manager.service;

import com.example.Task.manager.dto.TaskRegistrationDto;
import com.example.Task.manager.dto.TaskResponse;

import java.util.List;

public interface TaskService {

    TaskRegistrationDto createTask(TaskRegistrationDto registrationDto);
    List<TaskRegistrationDto> getTaskByCategoryId(int id);
    TaskRegistrationDto getTaskById(int taskId, int categoryId);
    TaskRegistrationDto updateTask(int categoryId, int tasksId, TaskRegistrationDto taskRegistrationDto);
    void deleteReview(int categoryId, int taskId);
}
