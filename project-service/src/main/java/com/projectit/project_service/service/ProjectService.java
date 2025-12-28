package com.projectit.project_service.service;

import com.projectit.project_service.domain.Category;
import com.projectit.project_service.domain.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    Project createProject(Project project) ;

    Project updateProject(Long idproject  , Project updatedProject);
    
    void deleteProject(Long idproject);
    
    List<Project> getProjectsByUserId(Long userId);
}
