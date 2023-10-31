package com.example.Task.manager;

import com.example.Task.manager.dto.TaskRegistrationDto;
import com.example.Task.manager.model.Category;
import com.example.Task.manager.model.Priority;
import com.example.Task.manager.repository.CategoryRepository;
import com.example.Task.manager.repository.TaskRepository;
import com.example.Task.manager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class TaskManagerApplicationTests {

	private final TaskService taskService;
	private final TaskRepository taskRepository;
	private final CategoryRepository categoryRepository;

	@Autowired
	public TaskManagerApplicationTests(TaskService taskService, TaskRepository taskRepository, CategoryRepository categoryRepository) {
		this.taskService = taskService;
		this.taskRepository = taskRepository;
		this.categoryRepository = categoryRepository;
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void testCreateTask() {
		TaskRegistrationDto registrationDto = new TaskRegistrationDto();
		registrationDto.setTitle("Sample Task");
		registrationDto.setDescription("Sample Description");
		registrationDto.setDueDate(LocalDate.now());
		registrationDto.setPriority(Priority.HIGH);
		registrationDto.setCategoryId(1L);
		Category category = new Category();
		category.setId(1L);
		category.setName("Work");
		categoryRepository.save(category);
		TaskRegistrationDto result = taskService.createTask(registrationDto);
		assertNotNull(result);
	}
}
