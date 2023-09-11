package com.example.Task.manager.service.Impl;

import com.example.Task.manager.dto.TaskRegistrationDto;
import com.example.Task.manager.exception.CategoryNotFoundException;
import com.example.Task.manager.exception.TaskNotFoundException;
import com.example.Task.manager.model.Category;
import com.example.Task.manager.model.Task;
import com.example.Task.manager.repository.CategoryRepository;
import com.example.Task.manager.repository.TaskRepository;
import com.example.Task.manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public List<TaskRegistrationDto> getTaskByCategoryId(Long id) {
        List<Task> tasks = taskRepository.findByCategoryId(id);
        return tasks.stream().map(task -> mapToDto(task)).collect(Collectors.toList());
    }

    @Override
    public TaskRegistrationDto getTaskById(Long taskId, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category with associated task not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task with associated Category not found"));
        if (!Objects.equals(task.getCategory().getId(), category.getId())){
            throw new TaskNotFoundException("This task does not belong to a category ");
        }
        return mapToDto(task);
    }

    @Override
    public TaskRegistrationDto updateTask(Long categoryId, Long tasksId, TaskRegistrationDto taskRegistrationDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("category with associated task not found"));
        Task task = taskRepository.findById(tasksId).orElseThrow(() -> new TaskNotFoundException("Task with associated category not found"));
        if (task.getCategory().getId() != category.getId()){
            throw new TaskNotFoundException("This task does not belong to a category");
        }
        task.setTitle(taskRegistrationDto.getTitle());
        task.setDescription(taskRegistrationDto.getDescription());
        task.setDueDate(taskRegistrationDto.getDueDate());
        task.setPriority(taskRegistrationDto.getPriority());
        Task updateTask = taskRepository.save(task);
        return mapToDto(updateTask);
    }

    @Override
    public void deleteTask(Long categoryId, Long taskId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("category with associated task not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task with associated category not found"));

        if (task.getCategory().getId() != category.getId()){
            throw new TaskNotFoundException("This task does not belong to a category");
        }
        taskRepository.delete(task);
    }

    private TaskRegistrationDto mapToDto(Task task){
       TaskRegistrationDto taskRegistrationDto = new TaskRegistrationDto();
        taskRegistrationDto.setId(task.getId());
        taskRegistrationDto.setTitle(task.getTitle());
        taskRegistrationDto.setDescription(task.getDescription());
        taskRegistrationDto.setDueDate(task.getDueDate());
        taskRegistrationDto.setPriority(task.getPriority());
        return taskRegistrationDto;
    }
}