package com.example.Task.manager.service;

import com.example.Task.manager.dto.CategoryRegistrationDto;
import com.example.Task.manager.dto.CategoryResponse;

public interface CategoryService {
    CategoryRegistrationDto createCategory(CategoryRegistrationDto categoryRegistrationDto);
    CategoryResponse getAllCategory(int pageNo, int pageSize);
}
