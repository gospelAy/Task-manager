package com.example.Task.manager.model;

public enum Priority {
    HIGH("High Priority"),
    MEDIUM("Medium Priority"),
    LOW("Low Priority");

    private final String displayName;

    Priority(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
