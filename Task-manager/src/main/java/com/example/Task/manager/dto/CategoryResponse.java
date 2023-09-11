package com.example.Task.manager.dto;


import lombok.Data;

import java.util.List;
@Data
public class CategoryResponse {
    private List<CategoryRegistrationDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean last;
}
