package com.projectit.task_service.repository;

import com.projectit.task_service.entity.Task;
import com.projectit.task_service.enums.TasksStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByProjectId(Long projectId);
    
    long countByProjectId(Long projectId);
    
    long countByProjectIdAndStatus(Long projectId, TasksStatus status);
}
