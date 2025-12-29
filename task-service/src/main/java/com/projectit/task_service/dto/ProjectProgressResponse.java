package com.projectit.task_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectProgressResponse {
    
    private Long projectId;
    private Long totalTasks;
    private Long completedTasks;
    private Integer progress;
}

