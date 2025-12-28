package com.projectit.task_service.service.impl;

import com.projectit.task_service.dto.TaskCreateRequest;
import com.projectit.task_service.dto.TaskResponse;
import com.projectit.task_service.entity.Task;
import com.projectit.task_service.enums.TasksStatus;
import com.projectit.task_service.repository.TaskRepository;
import com.projectit.task_service.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    
    private final TaskRepository taskRepository;
    
    @Override
    public TaskResponse createTask(TaskCreateRequest request) {
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .projectId(request.getProjectId())
                .dueDate(request.getDueDate())
                .status(TasksStatus.TODO)
                .build();
        
        Task savedTask = taskRepository.save(task);
        
        return TaskResponse.builder()
                .id(savedTask.getId())
                .title(savedTask.getTitle())
                .description(savedTask.getDescription())
                .status(savedTask.getStatus())
                .projectId(savedTask.getProjectId())
                .dueDate(savedTask.getDueDate())
                .build();
    }
}
