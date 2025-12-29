package com.projectit.task_service.controller;

import com.projectit.task_service.dto.ProjectProgressResponse;
import com.projectit.task_service.dto.TaskCreateRequest;
import com.projectit.task_service.dto.TaskResponse;
import com.projectit.task_service.dto.TaskStatusUpdateRequest;
import com.projectit.task_service.dto.TaskUpdateRequest;
import com.projectit.task_service.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    
    private final TaskService taskService;
    
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskCreateRequest request) {
        TaskResponse response = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{taskId}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(
            @PathVariable Long taskId,
            @Valid @RequestBody TaskStatusUpdateRequest request) {
        TaskResponse response = taskService.updateTaskStatus(taskId, request);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long taskId,
            @Valid @RequestBody TaskUpdateRequest request) {
        TaskResponse response = taskService.updateTask(taskId, request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskResponse>> getTasksByProjectId(@PathVariable Long projectId) {
        List<TaskResponse> tasks = taskService.getTasksByProjectId(projectId);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/project/{projectId}/progress")
    public ResponseEntity<ProjectProgressResponse> getProjectProgress(@PathVariable Long projectId) {
        ProjectProgressResponse response = taskService.getProjectProgress(projectId);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
