package com.projectit.project_service.service;

import com.projectit.project_service.domain.Category;
import com.projectit.project_service.domain.Project;
import com.projectit.project_service.dto.ProjectRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    Project createProject(ProjectRequestDTO projectDTO);
    
    Project updateProject(Long id, ProjectRequestDTO projectDTO);
    
    void deleteProject(Long id);
    
    List<Project> getProjectsByUserId(Long userId);
    
    List<Project> searchProjectsByTitle(String title);
    
    List<Project> filterProjectsByCategory(Long categoryId);
}
