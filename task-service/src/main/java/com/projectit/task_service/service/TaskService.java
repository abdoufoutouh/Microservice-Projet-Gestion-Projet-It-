package com.projectit.task_service.service;

import com.projectit.task_service.dto.ProjectProgressResponse;
import com.projectit.task_service.dto.TaskCreateRequest;
import com.projectit.task_service.dto.TaskResponse;
import com.projectit.task_service.dto.TaskStatusUpdateRequest;
import com.projectit.task_service.dto.TaskUpdateRequest;

import java.util.List;

public interface TaskService {
    
    TaskResponse createTask(TaskCreateRequest request);
    
    TaskResponse updateTaskStatus(Long taskId, TaskStatusUpdateRequest request);
    
    TaskResponse updateTask(Long taskId, TaskUpdateRequest request);
    
    List<TaskResponse> getTasksByProjectId(Long projectId);
    
    ProjectProgressResponse getProjectProgress(Long projectId);
    
    void deleteTask(Long taskId);
}
