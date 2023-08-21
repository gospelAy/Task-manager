package com.example.Task.manager.dto;

import lombok.Data;

import java.util.List;

@Data
public class TaskResponse {
    private List<TaskRegistrationDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
