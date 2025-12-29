package com.projectit.task_service.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateRequest {
    
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;
    
    private String description;
    
    private LocalDate dueDate;
}

