package com.projectit.task_service.service;

import com.projectit.task_service.dto.TaskCreateRequest;
import com.projectit.task_service.dto.TaskResponse;

public interface TaskService {
    
    TaskResponse createTask(TaskCreateRequest request);
}
