package com.projectit.task_service.service.impl;

import com.projectit.task_service.dto.ProjectProgressResponse;
import com.projectit.task_service.dto.TaskCreateRequest;
import com.projectit.task_service.dto.TaskResponse;
import com.projectit.task_service.dto.TaskStatusUpdateRequest;
import com.projectit.task_service.dto.TaskUpdateRequest;
import com.projectit.task_service.entity.Task;
import com.projectit.task_service.enums.TasksStatus;
import com.projectit.task_service.exception.TaskNotFoundException;
import com.projectit.task_service.repository.TaskRepository;
import com.projectit.task_service.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    
    @Override
    public TaskResponse updateTaskStatus(Long taskId, TaskStatusUpdateRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
        
        task.setStatus(request.getStatus());
        
        Task updatedTask = taskRepository.save(task);
        
        return TaskResponse.builder()
                .id(updatedTask.getId())
                .title(updatedTask.getTitle())
                .description(updatedTask.getDescription())
                .status(updatedTask.getStatus())
                .projectId(updatedTask.getProjectId())
                .dueDate(updatedTask.getDueDate())
                .build();
    }
    
    @Override
    @Transactional
    public TaskResponse updateTask(Long taskId, TaskUpdateRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
        
        if (request.getTitle() != null) {
            task.setTitle(request.getTitle());
        }
        
        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }
        
        if (request.getDueDate() != null) {
            task.setDueDate(request.getDueDate());
        }
        
        Task updatedTask = taskRepository.save(task);
        
        return TaskResponse.builder()
                .id(updatedTask.getId())
                .title(updatedTask.getTitle())
                .description(updatedTask.getDescription())
                .status(updatedTask.getStatus())
                .projectId(updatedTask.getProjectId())
                .dueDate(updatedTask.getDueDate())
                .build();
    }
    
    @Override
    public List<TaskResponse> getTasksByProjectId(Long projectId) {
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        
        return tasks.stream()
                .map(task -> TaskResponse.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .status(task.getStatus())
                        .projectId(task.getProjectId())
                        .dueDate(task.getDueDate())
                        .build())
                .collect(Collectors.toList());
    }
    
    @Override
    public ProjectProgressResponse getProjectProgress(Long projectId) {
        long totalTasks = taskRepository.countByProjectId(projectId);
        long completedTasks = taskRepository.countByProjectIdAndStatus(projectId, TasksStatus.DONE);
        
        int progress = totalTasks == 0 ? 0 : (int) Math.round((completedTasks * 100.0) / totalTasks);
        
        return ProjectProgressResponse.builder()
                .projectId(projectId)
                .totalTasks(totalTasks)
                .completedTasks(completedTasks)
                .progress(progress)
                .build();
    }
    
    @Override
    @Transactional
    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
        
        taskRepository.delete(task);
    }
}
