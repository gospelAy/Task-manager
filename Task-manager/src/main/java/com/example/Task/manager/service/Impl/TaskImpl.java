package com.example.Task.manager.service.Impl;

import com.example.Task.manager.dto.TaskRegistrationDto;
import com.example.Task.manager.exception.CategoryNotFoundException;
import com.example.Task.manager.model.Category;
import com.example.Task.manager.model.Task;
import com.example.Task.manager.repository.CategoryRepository;
import com.example.Task.manager.repository.TaskRepository;
import com.example.Task.manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskImpl implements TaskService {
    private TaskRepository taskRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public TaskImpl(TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public TaskRegistrationDto createTask(TaskRegistrationDto registrationDto) {
        Task task = new Task();
        task.setTitle(registrationDto.getTitle());
        task.setDescription(registrationDto.getDescription());
        task.setDueDate(registrationDto.getDueDate());
        task.setPriority(registrationDto.getPriority());
        Category category = (Category) categoryRepository.findById(registrationDto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        task.setCategory(category);
        Task newTask = taskRepository.save(task);
        registrationDto.setId(newTask.getId());
        return registrationDto;
    }

    @Override
    public List<TaskRegistrationDto> getTaskByCategoryId(int id) {
        return null;
    }

    @Override
    public TaskRegistrationDto getTaskById(int taskId, int categoryId) {
        return null;
    }

    @Override
    public TaskRegistrationDto updateTask(int categoryId, int tasksId, TaskRegistrationDto taskRegistrationDto) {
        return null;
    }

    @Override
    public void deleteReview(int categoryId, int taskId) {

    }

}