package com.example.Task.manager.service.Impl;

import com.example.Task.manager.dto.CategoryRegistrationDto;
import com.example.Task.manager.dto.CategoryResponse;
import com.example.Task.manager.exception.CategoryNotFoundException;
import com.example.Task.manager.model.Category;
import com.example.Task.manager.repository.CategoryRepository;
import com.example.Task.manager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public CategoryRegistrationDto createCategory(CategoryRegistrationDto categoryRegistrationDto) {
        Category category = new Category();
        category.setName(categoryRegistrationDto.getName());
        Category newCategory = categoryRepository.save(category);

        CategoryRegistrationDto categoryResponse = new CategoryRegistrationDto();
        categoryResponse.setId(newCategory.getId());
        categoryResponse.setName(newCategory.getName());
        return categoryResponse;
    }

    @Override
    public CategoryResponse getAllCategory(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Category> categories = categoryRepository.findAll(pageable);
        List<Category> listOfCategory = categories.getContent();
        List<CategoryRegistrationDto> content = listOfCategory.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(content);
        categoryResponse.setPageNo(categories.getNumber());
        categoryResponse.setPageSize(categories.getSize());
        categoryResponse.setTotalElement(categories.getTotalElements());
        categoryResponse.setTotalPages(categories.getTotalPages());
        categoryResponse.setLast(categories.isLast());
        return categoryResponse;
    }

    @Override
    public CategoryRegistrationDto getCategoryById(Long Id) {
        Category category = categoryRepository.findById(Id).orElseThrow(()-> new CategoryNotFoundException("Category could not be found"));
        return mapToDto(category);
    }

    @Override
    public CategoryRegistrationDto updateCategory(CategoryRegistrationDto categoryRegistrationDto, Long Id) {
        Category category = categoryRepository.findById(Id).orElseThrow(()-> new CategoryNotFoundException("Category could not be deleted"));
        category.setName(category.getName());
        Category updateCategory = categoryRepository.save(category);
        return mapToDto(updateCategory);
    }


    private CategoryRegistrationDto mapToDto(Category category){
        CategoryRegistrationDto categoryRegistrationDto = new CategoryRegistrationDto();
        categoryRegistrationDto.setId(category.getId());
        categoryRegistrationDto.setName(category.getName());
        return categoryRegistrationDto;
    }
}
