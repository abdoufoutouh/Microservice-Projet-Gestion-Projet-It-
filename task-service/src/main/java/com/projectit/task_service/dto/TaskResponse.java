package com.projectit.task_service.dto;

import com.projectit.task_service.enums.TasksStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    
    private Long id;
    private String title;
    private String description;
    private TasksStatus status;
    private Long projectId;
    private LocalDate dueDate;
}
